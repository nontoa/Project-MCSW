package com.app.BankApp.service.impl;

import com.app.BankApp.configuration.DatabaseConnection;
import com.app.BankApp.dto.Transfer;
import com.app.BankApp.dto.UserBank;
import com.app.BankApp.dto.ValidateUserDto;
import com.app.BankApp.service.api.IAccountService;
import com.app.BankApp.service.api.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserService implements IUserService {

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    DatabaseConnection databaseConnection;
    IAccountService accountService;

    public UserService(DatabaseConnection databaseConnection, AccountService accountService) {

        this.databaseConnection = databaseConnection;
        this.accountService = accountService;
    }


    @Override
    public Boolean isValidUser(ValidateUserDto user) {

        try{
            connection = databaseConnection.connect();
            String sql = "SELECT * FROM user_bank WHERE user_name = ? and password = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,user.getUserName());
            stmt.setString(2,user.getPassword());
            rs = stmt.executeQuery();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            if (rowCount > 0){
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }

        return Boolean.FALSE;

    }

    @Override
    public void createUser(UserBank user) {

        try{
            String account_id = accountService.createAccount(user.getId());
            if (Objects.isNull(account_id)){
                throw new UnexpectedException("Unexpected error with query database");
            }
            connection = databaseConnection.connect();
            StringBuilder sql = new StringBuilder("INSERT INTO user_bank (id, names, user_name, password, email, phone, account_id, profile, created_date)");
            sql.append(" VALUES (?,?,?,?,?,?,?,'USER',?)");
            Date currentDate = new java.util.Date();
            Date sqlDate = new java.sql.Date(currentDate.getTime());
            stmt = connection.prepareStatement(sql.toString());
            stmt.setString(1,user.getId());
            stmt.setString(2,user.getNames());
            stmt.setString(3,user.getUserName());
            stmt.setString(4,user.getPassword());
            stmt.setString(5,user.getEmail());
            stmt.setString(6,user.getPhone());
            stmt.setString(7,account_id);
            stmt.setDate(8, (java.sql.Date) sqlDate);
            stmt.executeUpdate();
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }
    }

    @Override
    public String getTotalBalance(String userId) {

        try{
            connection = databaseConnection.connect();
            String sql = "SELECT SUM(balance) AS balance FROM account WHERE user_bank_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getString("balance");
            }
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }
        return null;
    }


}
