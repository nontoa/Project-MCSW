package com.app.BankApp.service.impl;

import com.app.BankApp.configuration.DatabaseConnection;
import com.app.BankApp.dto.ValidateUserDto;
import com.app.BankApp.service.api.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Service
public class UserService implements IUserService {

    Connection connection = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    DatabaseConnection databaseConnection;

    public UserService(DatabaseConnection databaseConnection) {

        this.databaseConnection = databaseConnection;
    }


    @Override
    public Boolean isValidUser(ValidateUserDto user) {

        try{
            connection = databaseConnection.connect();
            String sql = "SELECT * FROM \"user\" WHERE user_name = ? and password = ?";
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
        }catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            closeConnections();
        }

        return Boolean.FALSE;

    }

    private void closeConnections(){

        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}
