package com.epam.task2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

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

        connections = new ArrayList<>();
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
                    pc = checkConnection(pc);
                    if (pc == null) {
                        connections.remove(i);
                    } else {
                        return pc;
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

    private PoolConnection checkConnection(PoolConnection pc) throws SQLException {
        if (!checkConnections) {
            return pc;
        } else {
            if (isHealthy(pc)) {
                return pc;
            } else {
                pc.expire();
                return null;
            }
        }
    }

    private boolean isHealthy(PoolConnection pc) {
        boolean isHealthy = true;
        try {
            if (pc.isClosed() && pc.getWarnings() != null) {
                isHealthy = false;
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage(), e);
            isHealthy = false;
        }
        return isHealthy;
    }

    public void removeExpired() {
        PoolConnection pc;

        long maxIdleDeadline = System.currentTimeMillis() - maxIdleTime;
        long maxUseDeadline = System.currentTimeMillis() - maxUseTime;

        synchronized (connections) {
            for (int i = connections.size() - 1; i >= 0; i--) {
                pc = connections.get(i);

                if (isUnusedConnection(pc, maxIdleDeadline) || isClosedConnection(pc, maxUseDeadline)) {
                    connections.remove(i);
                    try {
                        pc.expire();
                    } catch (SQLException ignore) {
                        logger.debug(ignore.getMessage(), ignore);
                    }
                }
            }

            if (connections.isEmpty() && cleaner != null) {
                cleaner.halt();
                cleaner = null;
            }
        }
    }

    private boolean isUnusedConnection(PoolConnection pc, long maxIdleDeadline) {
        return !pc.inUse() && pc.getTimeClosed() < maxIdleDeadline;
    }

    private boolean isClosedConnection(PoolConnection pc, long maxUseDeadline) {
        return maxUseTime >= 0 && pc.inUse() && pc.getTimeOpened() < maxUseDeadline;
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
                    logger.warn("Warning: forced closing of a connection still in use.");
                    logger.warn("Connection was acquired in:");
                }

                connections.remove(i);
                try {
                    pc.expire();
                } catch (SQLException ignore) {
                    logger.debug(ignore.getMessage(), ignore);
                }
            }
        }
    }

    static class PoolConnection implements Connection {
        protected Connection connection;
        protected boolean inUse;
        protected boolean autoCommit;
        protected long timeOpened;
        protected long timeClosed;

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

        public void expire() throws SQLException {
            connection.close();
            connection = null;
        }

        @Override
        public synchronized void close() throws SQLException {
            if (inUse) {
                timeClosed = System.currentTimeMillis();
                inUse = false;

                if (!autoCommit) {
                    setAutoCommit(true);
                }
            }
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
            this.autoCommit = connection.getAutoCommit();
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(
                int resultSetType,
                int resultSetConcurrency,
                int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(
                    resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(
                String sql,
                int resultSetType,
                int resultSetConcurrency,
                int resultSetHoldability)
                throws SQLException {
            return connection.prepareStatement(
                    sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public CallableStatement prepareCall(
                String sql,
                int resultSetType,
                int resultSetConcurrency,
                int resultSetHoldability)
                throws SQLException {
            return connection.prepareCall(
                    sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGenerateKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGenerateKeys);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
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

        @Override
        public void run() {
            while (!mustStop) {
                try {
                    sleep(cleaningInterval);
                } catch (InterruptedException ignore) {
                    logger.debug(ignore.getMessage(), ignore);
                    interrupt();
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
