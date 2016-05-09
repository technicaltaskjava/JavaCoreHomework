package javase.common.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Connection Pool gets connections when required, retrieves them and keeps active
 * to prevent reconnections
 * Created by Yury Vislobodsky on 05.05.2016.
 */
public class ConnectionPool {
    private BlockingQueue<Connection> poolFree = new LinkedBlockingQueue<>();
    private BlockingQueue<Connection> poolUsed = new LinkedBlockingQueue<>();
    private String dbDriver;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private int dbMinPoolSize;

    public ConnectionPool() throws ConnectionPoolException {
        ConnectionPoolProperties properties = ConnectionPoolProperties.getInstance();
        this.dbDriver = properties.getProperty("db.driver");
        this.dbUrl = properties.getProperty("db.url");
        this.dbUser = properties.getProperty("db.user");
        this.dbPassword = properties.getProperty("db.password");
        this.dbMinPoolSize = properties.getIntegerProperty("db.minpoolsize", 5);
        initConnectionPool();
    }

    private void initConnectionPool() throws ConnectionPoolException {
        try {
            Class.forName(dbDriver);
            for (int count = 0; count < dbMinPoolSize; count++) {
                poolFree.add(newConnection());
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("DB driver class not found", e);
        }
    }

    private Connection newConnection() throws ConnectionPoolException {
        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new ConnectionPoolException("New connection error", e);
        }
    }

    public synchronized Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        if (poolFree.isEmpty()) {
            connection = newConnection();
        } else {
            connection = poolFree.poll();
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new ConnectionPoolException("Set auto commit error", e);
            }
        }
        poolUsed.add(connection);
        return connection;
    }

    public synchronized void retrieveConnection(Connection connection) throws ConnectionPoolException {
        if (connection != null) {
            if (poolUsed.contains(connection)) {
                poolUsed.remove(connection);
                if (getCurrentPoolSize() < dbMinPoolSize) {
                    poolFree.add(connection);
                } else {
                    closeConnection(connection);
                }
            } else {
                throw new ConnectionPoolException("Connection is not in the pool");
            }
        }
    }

    private void closeConnection(Connection connection) throws ConnectionPoolException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection closing error", e);
        }
    }

    private int getCurrentPoolSize() {
        return poolUsed.size() + poolFree.size();
    }

    public void closeConnectionPool() throws ConnectionPoolException {
        if (!poolUsed.isEmpty()) {
            throw new ConnectionPoolException("Some connections are used");
        } else {
            while (!poolFree.isEmpty()) {
                closeConnection(poolFree.poll());
            }
        }
    }
}
