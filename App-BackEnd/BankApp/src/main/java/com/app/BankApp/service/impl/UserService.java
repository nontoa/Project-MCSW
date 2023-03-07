package com.app.BankApp.service.impl;

import com.app.BankApp.configuration.DatabaseConnection;
import com.app.BankApp.dto.UserBank;
import com.app.BankApp.dto.UserBankResponseDto;
import com.app.BankApp.dto.ValidateUserDto;
import com.app.BankApp.service.api.IAccountService;
import com.app.BankApp.service.api.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserService implements IUserService {

    BigDecimal ZERO = new BigDecimal(0);
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
    public String isValidUser(ValidateUserDto user) {

        try{
            connection = databaseConnection.connect();
            String sql = "SELECT * FROM user_bank WHERE user_name = ? and password = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,user.getUserName());
            stmt.setString(2,user.getPassword());
            rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getString("profile");
            }
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }

        return null;

    }

    @Override
    public String createUser(UserBank user) {

        try{
            connection = databaseConnection.connect();
            checkUserExistence(user.getId(), user.getUserName());
            String account_id = accountService.createAccount(user.getId());
            if (Objects.isNull(account_id)){
                throw new UnexpectedException("Unexpected error with query database");
            }
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
            return("User created");
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }
        return ("There was an exception");
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

    @Override
    public List<UserBankResponseDto> getAllUsers() {

        try{
            connection = databaseConnection.connect();
            String sql = "SELECT * FROM user_bank WHERE profile = 'USER'";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<UserBankResponseDto> users = new ArrayList<>();
            while (rs.next()) {
                var user = UserBankResponseDto
                        .builder()
                        .id(rs.getString("id"))
                        .names(rs.getString("names"))
                        .userName(rs.getString("user_name"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .createdDate(rs.getDate("created_date"))
                        .build();
                users.add(user);
            }
            return users;
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }
        return null;

    }

    @Override
    public String modifyBalance(String userId, String accountId, String amount, String type) {

        try{
            connection = databaseConnection.connect();
            var newBalance = new BigDecimal(amount);
            if (newBalance.compareTo(ZERO) <= 0){
                throw new Exception("New balance must be greater than 0");
            }
            checkAccountOwnership(userId, accountId);
            String sql;
            if (type.equals("BALANCE")){
                sql = "UPDATE account SET balance = ? WHERE id = ?";
            } else {
                sql = "UPDATE account SET overdraft_balance = ? WHERE id = ?";
            }
            stmt = connection.prepareStatement(sql);
            stmt.setBigDecimal(1, newBalance);
            stmt.setString(2, accountId);
            stmt.executeUpdate();
            return "Amount modified";
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            databaseConnection.closeConnections(rs, stmt, connection);
        }
        return "There was an exception";
    }

    private void checkAccountOwnership(String userId, String accountId) throws Exception{

        String sql = "SELECT * FROM user_bank WHERE id = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1, userId);
        rs = stmt.executeQuery();
        while (rs.next()) {
            if (!rs.getString("account_id").equals(accountId)){
                throw new Exception("The account does not belong to the given user");
            }
        }
    }

    private void checkUserExistence(String userId, String userName) throws Exception {

        String sql = "SELECT * FROM user_bank WHERE id = ? or user_name = ?";
        stmt = connection.prepareStatement(sql);
        stmt.setString(1,userId);
        stmt.setString(2, userName);
        rs = stmt.executeQuery();
        while (rs.next()) {
            throw new Exception("User already exist");
        }
    }


}
