package com.epam.task2.util;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by Olga Kramska on 09-May-16.
 */

public class ConnectionPool {
    protected final List<PoolConnection> connections;
    protected String url;
    protected String user;
    protected String password;
    protected boolean checkConnections = true;
    protected long cleaningInterval = 30000;
    protected long maxIdleTime = 30000;

    protected long maxUseTime = -1;

    protected boolean draining = false;

    protected PoolCleaner cleaner;

    public ConnectionPool(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

        connections = new ArrayList();
    }

    public void setCheckConnections(boolean checkConnections) {
        this.checkConnections = checkConnections;
    }

    public void setCleaningInterval(long cleaningInterval) {
        this.cleaningInterval = cleaningInterval;
    }


    public void setMaxIdleTime(long maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }


    public void setMaxUseTime(long maxUseTime) {
        this.maxUseTime = maxUseTime;
    }

    public Connection getConnection() throws SQLException {
        if (draining) {
            throw new SQLException("ConnectionPool was drained");
        }

        synchronized (connections) {
            PoolConnection pc;

            for (int i = 0; i < connections.size(); i++) {
                pc = connections.get(i);

                if (pc.lease()) {

                    if (!checkConnections) {
                        return pc;
                    } else {
                        boolean isHealthy = true;

                        try {
                            if (pc.isClosed() && pc.getWarnings() != null) {
                                isHealthy = false;
                            }
                        } catch (SQLException sqle) {
                            isHealthy = false;
                        }

                        if (isHealthy) {
                            return pc;
                        } else {
                            pc.expire();

                            connections.remove(i);
                        }
                    }
                }
            }
        }

        Connection con = DriverManager.getConnection(url, user, password);
        PoolConnection pc = new PoolConnection(con);
        pc.lease();

        synchronized (connections) {
            connections.add(pc);

            if (cleaner == null) {
                cleaner = new PoolCleaner(cleaningInterval);
                cleaner.start();
            }
        }

        return pc;
    }

    public void removeExpired() {
        PoolConnection pc;

        long maxIdleDeadline = System.currentTimeMillis() - maxIdleTime;
        long maxUseDeadline = System.currentTimeMillis() - maxUseTime;

        synchronized (connections) {
            for (int i = connections.size() - 1; i >= 0; i--) {
                pc = connections.get(i);

                if (!pc.inUse() && pc.getTimeClosed() < maxIdleDeadline) {
                    connections.remove(i);
                    try {
                        pc.expire();
                    } catch (SQLException ignore) {
                    }
                } else if (
                        maxUseTime >= 0 &&
                                pc.inUse() &&
                                pc.getTimeOpened() < maxUseDeadline) {
                    System.err.println("Warning: forced closing of a connection that has been in use too long.");
                    System.err.println("Connection was acquired in:");
                    pc.printStackTrace();
                    System.err.println();

                    connections.remove(i);
                    try {
                        pc.expire();
                    } catch (SQLException ignore) {
                    }
                }
            }

            if (connections.size() == 0 && cleaner != null) {
                cleaner.halt();
                cleaner = null;
            }
        }
    }

    public int getPoolSize() {
        synchronized (connections) {
            return connections.size();
        }
    }

    public void drain() {
        draining = true;

        if (cleaner != null) {
            cleaner.halt();
        }

        synchronized (connections) {
            for (int i = connections.size() - 1; i >= 0; i--) {
                PoolConnection pc = connections.get(i);

                if (pc.inUse()) {
                    System.err.println("Warning: forced closing of a connection still in use.");
                    System.err.println("Connection was acquired in:");
                    pc.printStackTrace();
                    System.err.println();
                }

                connections.remove(i);
                try {
                    pc.expire();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    protected void finalize() {
        drain();
    }

    static class PoolConnection implements Connection {
        protected Connection connection;
        protected boolean inUse;
        protected boolean autoCommit;
        protected long timeOpened;
        protected long timeClosed;
        private Throwable throwable;

        public PoolConnection(Connection conn) {
            connection = conn;
            inUse = false;
            autoCommit = true;
        }

        public synchronized boolean lease() {
            if (inUse) {
                return false;
            } else {
                inUse = true;
                timeOpened = System.currentTimeMillis();
                return true;
            }
        }

        public boolean inUse() {
            return inUse;
        }

        public synchronized long getTimeOpened() {
            return timeOpened;
        }

        public synchronized long getTimeClosed() {
            return timeClosed;
        }

        public void expire()
                throws SQLException {
            connection.close();
            connection = null;
        }

        public void printStackTrace() {
            throwable.printStackTrace(System.err);
        }

        public synchronized void close()
                throws SQLException {
            if (inUse) {
                timeClosed = System.currentTimeMillis();
                inUse = false;

                if (!autoCommit) {
                    setAutoCommit(true);
                }
            }
        }

        public Statement createStatement()
                throws SQLException {
            throwable = new Throwable();
            return connection.createStatement();
        }

        public PreparedStatement prepareStatement(String sql)
                throws SQLException {
            throwable = new Throwable();
            return connection.prepareStatement(sql);
        }

        public CallableStatement prepareCall(String sql)
                throws SQLException {
            return connection.prepareCall(sql);
        }

        public String nativeSQL(String sql)
                throws SQLException {
            return connection.nativeSQL(sql);
        }

        public void setAutoCommit(boolean autoCommit)
                throws SQLException {
            connection.setAutoCommit(autoCommit);
            this.autoCommit = connection.getAutoCommit();
        }

        public boolean getAutoCommit()
                throws SQLException {
            return connection.getAutoCommit();
        }

        public void commit()
                throws SQLException {
            connection.commit();
        }

        public void rollback()
                throws SQLException {
            connection.rollback();
        }

        public boolean isClosed()
                throws SQLException {
            return connection.isClosed();
        }

        public DatabaseMetaData getMetaData()
                throws SQLException {
            return connection.getMetaData();
        }

        public void setReadOnly(boolean readOnly)
                throws SQLException {
            connection.setReadOnly(readOnly);
        }

        public boolean isReadOnly()
                throws SQLException {
            return connection.isReadOnly();
        }

        public void setCatalog(String catalog)
                throws SQLException {
            connection.setCatalog(catalog);
        }

        public String getCatalog()
                throws SQLException {
            return connection.getCatalog();
        }

        public void setTransactionIsolation(int level)
                throws SQLException {
            connection.setTransactionIsolation(level);
        }

        public int getTransactionIsolation()
                throws SQLException {
            return connection.getTransactionIsolation();
        }

        public SQLWarning getWarnings()
                throws SQLException {
            return connection.getWarnings();
        }

        public void clearWarnings()
                throws SQLException {
            connection.clearWarnings();
        }

        public Statement createStatement(
                int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        public PreparedStatement prepareStatement(
                String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        public CallableStatement prepareCall(
                String sql, int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        public Map<String, Class<?>> getTypeMap()
                throws SQLException {
            return connection.getTypeMap();
        }

        public void setTypeMap(Map<String, Class<?>> map)
                throws SQLException {
            connection.setTypeMap(map);
        }

        public void setHoldability(int holdability)
                throws SQLException {
            connection.setHoldability(holdability);
        }

        public int getHoldability()
                throws SQLException {
            return connection.getHoldability();
        }

        public Savepoint setSavepoint()
                throws SQLException {
            return connection.setSavepoint();
        }

        public Savepoint setSavepoint(String name)
                throws SQLException {
            return connection.setSavepoint(name);
        }

        public void rollback(Savepoint savepoint)
                throws SQLException {
            connection.rollback(savepoint);
        }

        public void releaseSavepoint(Savepoint savepoint)
                throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        public Statement createStatement(
                int resultSetType,
                int resultSetConcurrency,
                int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(
                    resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public PreparedStatement prepareStatement(
                String sql,
                int resultSetType,
                int resultSetConcurrency,
                int resultSetHoldability)
                throws SQLException {
            return connection.prepareStatement(
                    sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public CallableStatement prepareCall(
                String sql,
                int resultSetType,
                int resultSetConcurrency,
                int resultSetHoldability)
                throws SQLException {
            return connection.prepareCall(
                    sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public PreparedStatement prepareStatement(
                String sql, int autoGenerateKeys)
                throws SQLException {
            return connection.prepareStatement(sql, autoGenerateKeys);
        }

        public PreparedStatement prepareStatement(
                String sql, int[] columnIndexes)
                throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        public PreparedStatement prepareStatement(
                String sql, String[] columnNames)
                throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        public Clob createClob() throws SQLException {
            return null;
        }

        public Blob createBlob() throws SQLException {
            return null;
        }

        public NClob createNClob() throws SQLException {
            return null;
        }

        public SQLXML createSQLXML() throws SQLException {
            return null;
        }

        public boolean isValid(int timeout) throws SQLException {
            return false;
        }

        public void setClientInfo(String name, String value) throws SQLClientInfoException {
        }

        public void setClientInfo(Properties properties) throws SQLClientInfoException {
        }

        public String getClientInfo(String name) throws SQLException {
            return null;
        }

        public Properties getClientInfo() throws SQLException {
            return null;
        }

        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return null;
        }

        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return null;
        }

        @Override
        public void setSchema(String schema) throws SQLException {

        }

        @Override
        public String getSchema() throws SQLException {
            return null;
        }

        @Override
        public void abort(Executor executor) throws SQLException {

        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return 0;
        }

        public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
        }

        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
    }

    class PoolCleaner extends Thread {

        protected long cleaningInterval;
        protected boolean mustStop;

        public PoolCleaner(long cleaningInterval) {
            if (cleaningInterval < 0) {
                throw new IllegalArgumentException("cleaningInterval must be >= 0");
            }
            mustStop = false;
            this.cleaningInterval = cleaningInterval;

            setDaemon(true);
        }

        public void run() {
            while (!mustStop) {
                try {
                    sleep(cleaningInterval);
                } catch (InterruptedException ignore) {
                }

                if (mustStop) {
                    break;
                }

                removeExpired();
            }
        }

        public void halt() {
            mustStop = true;
            synchronized (this) {
                this.interrupt();
            }
        }
    }
}
