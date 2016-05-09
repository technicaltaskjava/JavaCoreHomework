package com.epam.dao;

import com.epam.DbProperties;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

public class DbConnectionPool {
    Logger log = Logger.getLogger(DbConnectionPool.class.getName());
    private String dbDriver;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private int maxConnections;
    private Driver driver;
    private ConcurrentLinkedQueue freeConnections;
    private int connectionsCount;

    public DbConnectionPool() {
        freeConnections = new ConcurrentLinkedQueue();
        dbDriver = DbProperties.getINSTANCE().getDbDriver();
        dbUrl = DbProperties.getINSTANCE().getDbUrl();
        dbUser = DbProperties.getINSTANCE().getDbUser();
        dbPassword = DbProperties.getINSTANCE().getDbPassword();
        maxConnections = DbProperties.getINSTANCE().getDbPoolSize();
        connectionsCount = 0;
        try {
            driver = (Driver) Class.forName(dbDriver).newInstance();
            DriverManager.registerDriver(driver);
        }
        catch (Exception poolException) {
            log.info(String.valueOf(poolException));
        }
    }

    private Connection newConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); //NOSONAR -- close at destroy()
        }
        catch (Exception poolException) {
            log.info(String.valueOf(poolException));
            return null;
        }
        return connection;
    }

    public synchronized void freeConnection(Connection connection) {
        freeConnections.add(connection);
        connectionsCount--;
        notifyAll();
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        if (!freeConnections.isEmpty()) {
            connection = (Connection) freeConnections.remove();
            try {
                if (connection.isClosed()) {
                    connection = getConnection();
                }
            }
            catch (Exception poolException) {
                log.info(String.valueOf(poolException));
                connection = getConnection();
            }
            return connection;
        }
        if (connectionsCount < maxConnections) {
            connection = newConnection();
            System.out.println("New connection created");
        }
        if (connection != null) {
            connectionsCount++;
        }
        return connection;
    }

    public void destroy() {
        closeAll();
        try {
            DriverManager.deregisterDriver(driver);
            return;
        }
        catch (Exception poolException) {
            log.info(String.valueOf(poolException));
            return;
        }
    }

    private synchronized void closeAll() {
        while (!freeConnections.isEmpty()) {
            Connection connection = (Connection) freeConnections.remove();
            try {
                connection.close();
            }
            catch (Exception poolException) {
                log.info(String.valueOf(poolException));
            }
        }
    }
}