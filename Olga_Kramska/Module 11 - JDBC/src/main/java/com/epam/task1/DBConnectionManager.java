package com.epam.task1;

import com.epam.task2.util.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Olga Kramska on 08-May-16.
 */
public class DBConnectionManager {

    private DBConnectionManager() {
    }

    public static Connection getConnection() throws SQLException {
        PropertiesReader.load("database.properties");
        String driver = PropertiesReader.getProperty("jdbc.drivers");
        if (driver != null) {
            System.setProperty("jdbc.drivers", driver);
        }
        String url = PropertiesReader.getProperty("jdbc.url");
        String username = PropertiesReader.getProperty("jdbc.username");
        String password = PropertiesReader.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }
}
