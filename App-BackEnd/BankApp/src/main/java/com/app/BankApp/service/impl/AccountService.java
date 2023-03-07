package com.app.BankApp.service.impl;

import com.app.BankApp.configuration.DatabaseConnection;
import com.app.BankApp.service.api.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Slf4j
@Service
public class AccountService implements IAccountService {

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    DatabaseConnection databaseConnection;

    public AccountService(DatabaseConnection databaseConnection) {

        this.databaseConnection = databaseConnection;
    }

    @Override
    public String createAccount(String userId) {

        try{
            connection = databaseConnection.connect();
            Integer account_id = getMaxAccountId();
            if (Objects.isNull(account_id)){
                throw new UnexpectedException("Unexpected error with query database");
            }
            StringBuilder sql = new StringBuilder("INSERT INTO account (id, user_bank_id, balance, overdraft_balance)");
            sql.append(" VALUES (?,?,'0','0')");
            account_id += 1;
            stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1, account_id.toString());
            stmt.setString(2, userId);
            stmt.executeUpdate();
            return account_id.toString();
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }
        return null;
    }

    public Integer getMaxAccountId() throws SQLException {

        Integer maxAccountId;
        String sql = "select max(id) as max_id from account";
        stmt = connection.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            maxAccountId = Integer.parseInt(rs.getString("max_id"));
            return maxAccountId;
        }
        return null;
    }


}
