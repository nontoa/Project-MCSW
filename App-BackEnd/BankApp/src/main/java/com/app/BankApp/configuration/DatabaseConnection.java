package com.app.BankApp.configuration;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConnection {

    String url = "jdbc:postgresql://localhost:5432/bankapp";
    String user = "postgres";
    String password = "postgres";

    public Connection connect() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url,user, password);
        return connection;
    }

}
