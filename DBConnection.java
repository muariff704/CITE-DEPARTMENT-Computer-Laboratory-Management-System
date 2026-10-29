package citefilestorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/cite_file_storage";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static void main(String[] args) {

    Connection conn = DBConnection.getConnection();

    if (conn != null) {
        System.out.println("DATABASE CONNECTED SUCCESSFULLY!");
    } else {
        System.out.println("DATABASE CONNECTION FAILED!");
    }
}

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            System.out.println(e.getMessage());
            return null;
        }
    }
}