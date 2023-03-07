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

    BigDecimal ZERO = new BigDecimal(0);
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
                        .amount(rs.getBigDecimal("amount"))
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
    public String doTransfer(Transfer transfer) {

        try{
            if (transfer.getAmount().compareTo(ZERO) <= 0){
                throw new Exception("Amount must be greater than 0");
            }
            connection = databaseConnection.connect();
            checkBalanceFromOriginAccount(transfer.getOriginAccount(), transfer.getAmount());
            updateAmountToDestinationAccount(transfer.getDestinationAccount(), transfer.getAmount());
            StringBuilder sql = new StringBuilder("INSERT INTO transfer (id, origin_account, destination_account, amount, created_date)");
            sql.append(" VALUES (?,?,?,?,?)");
            Date currentDate = new java.util.Date();
            Date sqlDate = new java.sql.Date(currentDate.getTime());
            stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2,transfer.getOriginAccount());
            stmt.setString(3,transfer.getDestinationAccount());
            stmt.setBigDecimal(4, transfer.getAmount());
            stmt.setDate(5, (java.sql.Date) sqlDate);
            stmt.executeUpdate();
            return "Transfer completed successfully";
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }
        return "there was an exception";
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

    private void checkBalanceFromOriginAccount(String originAccount, BigDecimal amount) throws Exception {

        var balances = getAccountBalance(originAccount);
        var balance = balances[0];
        var overDraftBalance = balances[1];
        var balanceOp = balance.subtract(amount);
        var balanceAndOverDraftBalanceOp = (balance.add(overDraftBalance)).subtract(amount);
        if (balanceOp.compareTo(ZERO) >= 0 ){
            updateAccountBalance(originAccount, balance.subtract(amount), overDraftBalance);
        }else if(balanceAndOverDraftBalanceOp.compareTo(ZERO) >= 0){
            updateAccountBalance(originAccount, ZERO, balanceAndOverDraftBalanceOp);
        }else {
            throw new Exception("Transfer cannot be processed");
        }
    }

    private void updateAmountToDestinationAccount(String destinationAccount, BigDecimal amount) throws Exception {

        var balances = getAccountBalance(destinationAccount);
        updateAccountBalance(destinationAccount, balances[0].add(amount), balances[1]);
    }

    private BigDecimal[] getAccountBalance(String accountId) throws SQLException {

        String sql = "SELECT balance, overdraft_balance FROM account WHERE id = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, accountId);
        rs = stmt.executeQuery();
        var balances = new BigDecimal[2];
        while (rs.next()) {
            var balance = rs.getBigDecimal("balance");
            var overDraftBalance = rs.getBigDecimal("overdraft_balance");
            balances[0] = balance;
            balances[1] = overDraftBalance;
        }
        return balances;
    }

    private void updateAccountBalance(String originAccount,
                                      BigDecimal newBalance,
                                      BigDecimal newOverDraftBalance) throws Exception {

        StringBuilder sql = new StringBuilder("UPDATE account SET balance = ?, overdraft_balance = ? WHERE id = ?");
        stmt = connection.prepareStatement(sql.toString());
        stmt.setBigDecimal(1, newBalance);
        stmt.setBigDecimal(2, newOverDraftBalance);
        stmt.setString(3, originAccount);
        stmt.executeUpdate();
    }

}
