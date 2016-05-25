package com.epam.dao;

import java.sql.Connection;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 * Created by Oleg on 5/25/2016.
 */
public class DBConnectionManager {
    private JdbcConnectionPool connectionPool;

    public DBConnectionManager(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException {
        connectionPool = JdbcConnectionPool.create(dbURL, user, pwd);
    }

    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }
}
