package com.data.mydao.mypool;


import com.data.mydao.exeptions.MyJDBCException;
import com.data.mydao.factory.DAOFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class MyConnectionPool {
    private  ArrayDeque<Connection> availableConnection = new ArrayDeque();
    private  ArrayDeque<Connection> usedConnection = new ArrayDeque();

    public MyConnectionPool(int initConnCnt) {
        for (int start = 1; start < initConnCnt; start++) {
            availableConnection.add(getConnection());
        }
    }

    public MyConnectionPool(){
        for (int start = 1; start < 20; start++) {
            availableConnection.add(getConnection());
        }

    }

    private  synchronized Connection getConnection() {
        Connection conn = null;
        DAOFactory base = DAOFactory.getInstance("javabase");
        try {
            conn = base.getConnection();
        } catch (Exception e) {
            throw new MyJDBCException(e);
        }
        return conn;
    }

    public synchronized Connection retrieve() throws SQLException {
        Connection newConn;
        if (availableConnection.isEmpty()) {
            newConn = getConnection();
        } else {
            newConn = availableConnection.getLast();
            availableConnection.remove(newConn);
            usedConnection.add(newConn);
        }
        return newConn;
    }

    public  synchronized void putBack(Connection connection) {
        if (connection != null) {
            if (usedConnection.remove(connection)) {
                availableConnection.add(connection);
            }else {
                throw new NullPointerException("Connection not in the used Conns array");
            }
        }
    }


}
