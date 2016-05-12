package com.epam.task2.util;

import com.epam.task2.exc.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Olga Kramska on 09-May-16.
 */

public class ConnectionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

    private ConnectionPool connectionPool;

    private ConnectionManager() {
        init();
    }

    private void init() {
        PropertiesReader.load("database.properties");
        String driver = PropertiesReader.getProperty("jdbc.drivers");
        if (driver != null) {
            System.setProperty("jdbc.drivers", driver);
        }
        String url = PropertiesReader.getProperty("jdbc.url");
        String username = PropertiesReader.getProperty("jdbc.username");
        String password = PropertiesReader.getProperty("jdbc.password");

        connectionPool = new ConnectionPool(url, username, password);
    }

    private static class ConnectionPoolHolder {
        public static final ConnectionManager INSTANCE = new ConnectionManager();

        private ConnectionPoolHolder() {
        }
    }

    public static ConnectionManager getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }

    public Connection getConnection() {
        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }
}
