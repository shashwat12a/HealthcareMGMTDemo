package com.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // MySQL database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/healthcare";  // Change localhost to your DB host
    private static final String DB_USER = "root";  // Use your MySQL username
    private static final String DB_PASSWORD = "password";  // Use your MySQL password

    /**
     * Establishes a connection to the MySQL database.
     * @return Connection object to the database
     * @throws SQLException if there is an error establishing the connection
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);  // Return the connection object
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found.", e);
        }
    }
}