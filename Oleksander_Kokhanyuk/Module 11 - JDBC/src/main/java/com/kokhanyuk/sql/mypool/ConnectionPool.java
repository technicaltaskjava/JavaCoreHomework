package com.kokhanyuk.sql.mypool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ConnectionPool
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class ConnectionPool {

    private BlockingQueue<Connection> availableConns;
    private BlockingQueue<Connection> usedConns;
    private String url;
    private int initConnCnt;
    private static final ConnectionPool INSTANSE = new ConnectionPool();
    static Logger log = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() {

    }

    public static ConnectionPool getConnectionPool() {
        return INSTANSE;
    }

    public void setConnectionPool(String url, String driver, int initConnCnt) {
        availableConns = new ArrayBlockingQueue(initConnCnt);
        usedConns = new ArrayBlockingQueue(initConnCnt);
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.warn(e.getMessage(), e);
        }
        this.url = url;
        this.initConnCnt = initConnCnt;
        for (int i = 0; i < initConnCnt; i++) {
            availableConns.add(getConnection());
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            log.warn(e.getMessage(), e);
        }
        return conn;
    }

    public synchronized Connection retrieve() throws SQLException {
        Connection newConn;
        if (availableConns.isEmpty()) {
            newConn = getConnection();
        } else {
            newConn = availableConns.element();
            availableConns.remove(newConn);
            usedConns.add(newConn);
        }
        return newConn;
    }

    public synchronized void putback(Connection c) {
        if (c != null && usedConns.remove(c) && availableConns.size() != initConnCnt) {
            availableConns.add(c);
        }
    }

    public int getAvailableConnsCnt() {
        return availableConns.size();
    }
}
