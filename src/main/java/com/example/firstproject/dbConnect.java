
package com.example.firstproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class dbConnect {
    public Connection dbLink;

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Warehouse?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Bhoomi@0503";
    public Connection dbConn(){


        try {  dbLink = DriverManager.getConnection("jdbc:mysql://localhost:3306/Warehouse?useSSL=false", "root", "Bhoomi@0503");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  dbLink;
    }
}
