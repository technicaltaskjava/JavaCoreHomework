package com.kokhanyuk.sql.mypool;

import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * Searcher
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Searcher extends Thread {

    static Logger log = Logger.getLogger(Searcher.class);
    private String tableName;
    private int id;
    private String columnName;

    public Searcher(String tableName, int id, String columnName) {
        this.tableName = tableName;
        this.id = id;
        this.columnName = columnName;
        start();
    }

    @Override
    public void run() {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        FortuneCookies us = new FortuneCookies();
        Connection conn;
        try {
            conn = us.findById(connectionPool.retrieve(), tableName, id, columnName);
            connectionPool.putback(conn);
        } catch (InterruptedException e) {
            log.warn(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}
