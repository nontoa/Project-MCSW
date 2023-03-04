package com.app.BankApp.service.impl;

import com.app.BankApp.configuration.DatabaseConnection;
import com.app.BankApp.dto.Transfer;
import com.app.BankApp.service.api.ITransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Service
public class TransferService implements ITransferService {

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    DatabaseConnection databaseConnection;

    public TransferService(DatabaseConnection databaseConnection) {

        this.databaseConnection = databaseConnection;
    }

    @Override
    public List<Transfer> getAllTransfersByUserId(String userId) {
        try{
            connection = databaseConnection.connect();
            String account_id = getUserAccount(userId);
            String sql = "SELECT * FROM transfer WHERE origin_account = ? or destination_account = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,account_id);
            stmt.setString(2,account_id);
            rs = stmt.executeQuery();
            List<Transfer> transfers = new ArrayList<>();
            while (rs.next()) {
                transfers.add(Transfer
                        .builder()
                        .id(rs.getString("id"))
                        .originAccount(rs.getString("origin_account"))
                        .destinationAccount(rs.getString("destination_account"))
                        .amount(rs.getString("amount"))
                        .createdDate(rs.getDate("created_date"))
                        .build());
            }
            return transfers;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }

        return null;
    }

    @Override
    public void doTransfer(Transfer transfer) {

        try{
            //TODO: It's missing sum the amount to the destination account and subtract to the origin account
            //TODO: It's missing check the origin account balance
            //TODO: It's missing check the overdraft_balance
            connection = databaseConnection.connect();
            StringBuilder sql = new StringBuilder("INSERT INTO transfer (id, origin_account, destination_account, amount, created_date)");
            sql.append(" VALUES (?,?,?,?,?)");
            Date currentDate = new java.util.Date();
            Date sqlDate = new java.sql.Date(currentDate.getTime());
            stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2,transfer.getOriginAccount());
            stmt.setString(3,transfer.getDestinationAccount());
            stmt.setBigDecimal(4, new BigDecimal(transfer.getAmount()));
            stmt.setDate(5, (java.sql.Date) sqlDate);
            stmt.executeUpdate();
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }
    }


    private String getUserAccount(String userId) throws SQLException {

        String sql = "SELECT id FROM account WHERE user_bank_id = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, userId);
        rs = stmt.executeQuery();
        while (rs.next()) {
            return rs.getString("id");
        }
        return null;
    }
}
