package com.epam.task2;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 10.05.2016.
 */
public class ConnectionPool {

    List<Connection> availableConnections = new ArrayList<>();
    private static final Logger log = Logger.getLogger(ConnectionPool.class);

    public ConnectionPool() {
        initializeConnectionPool();
    }

    private void initializeConnectionPool() {
        while(!checkIfConnectionPoolIsFull()) {
            availableConnections.add(createNewConnectionForPool());
        }
    }

    private synchronized boolean checkIfConnectionPoolIsFull() {
        final int maxPoolSize = Configuration.getInstance().getConnections();
        return availableConnections.size() > maxPoolSize;
    }

    private Connection createNewConnectionForPool() {
        Configuration config = Configuration.getInstance();
        try {
            Class.forName(config.getDrv());
            return DriverManager.getConnection(config.getUrl(), config.getUserName(), config.getPas());
        } catch (ClassNotFoundException | SQLException  e) {
            log.error(e);
            return null;
        }
    }

    public synchronized Connection getConnectionFromPool() {
        Connection connection = null;
        if(!availableConnections.isEmpty()) {
            connection = availableConnections.get(0);
            availableConnections.remove(0);
        }
        return connection;
    }

    public synchronized void returnConnectionToPool(Connection connection) {
        availableConnections.add(connection);
    }

}