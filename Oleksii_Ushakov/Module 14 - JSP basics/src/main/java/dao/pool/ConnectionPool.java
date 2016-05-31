package dao.pool;

import dao.exception.ConnectionPoolException;
import dao.pool.connection.PoolConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Alexey Ushakov
 */
public class ConnectionPool {
    private static final int POOL_SIZE = 5;
    // TODO: 28.05.16 java.lang.NoClassDefFoundError: org/apache/log4j/Logger ?!
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);
    private static BlockingQueue<PoolConnection> openConnectionQueue;
    private static BlockingQueue<PoolConnection> usedConnectionQueue;

    public ConnectionPool(String connectionString, String username, String password) {
        openConnectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);
        usedConnectionQueue = new LinkedBlockingQueue<>(POOL_SIZE);

        try {

            Class.forName("org.h2.Driver");
            for (int i = 0; i < POOL_SIZE; i++) {
                openConnectionQueue.put(new PoolConnection(DriverManager.getConnection(connectionString, username, password), this));
            }

        } catch (SQLException | InterruptedException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
    }

    public Connection getConnection() {
        try {

            PoolConnection opened = openConnectionQueue.take();

            opened.clearWarnings();
            usedConnectionQueue.put(opened);

            return opened;

        } catch (InterruptedException | SQLException e) {
            logger.error(e);
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }

    public void put(PoolConnection connection) {
        try {
            if (connection != null) {
                connection.clearWarnings();
                usedConnectionQueue.remove(connection);
                openConnectionQueue.put(connection);
            }
        } catch (InterruptedException | SQLException e) {
            logger.error(e);
        }
    }

    public void close() {
        try {

            for (Connection connection : usedConnectionQueue) {
                connection.close();
            }

            if (!usedConnectionQueue.isEmpty()) {
                for (Connection connection : openConnectionQueue) {
                    connection.close();
                }
            }

        } catch (SQLException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }

    public int size() {
        return POOL_SIZE;
    }
}
