package com.app.BankApp.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

@Slf4j
@Configuration
public class DatabaseConnection {

    String url = "jdbc:postgresql://localhost:5432/BankApp";
    String user = "postgres2";
    String password = "postgres2";

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
