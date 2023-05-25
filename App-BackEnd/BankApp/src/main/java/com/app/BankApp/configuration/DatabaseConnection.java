package com.app.BankApp.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

@Slf4j
@Configuration
public class DatabaseConnection {

    @Value("${database.url}")
    private String url;

    @Value("${database.user}")
    private String user;

    @Value("${database.password}")
    private String password;

    public Connection connect() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url,user, password);
        return connection;
    }

    public void closeConnections(ResultSet rs, PreparedStatement stmt, Connection connection){

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
