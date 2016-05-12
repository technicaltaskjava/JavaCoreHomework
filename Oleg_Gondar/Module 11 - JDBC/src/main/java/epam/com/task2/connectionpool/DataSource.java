package epam.com.task2.connectionpool;

import java.sql.Connection;

public class DataSource {

    static ConnectionPool pool = new ConnectionPool();

    private DataSource() {
    }

    public static Connection getConnection() {

        Connection connection;
        connection = pool.getConnectionFromPool();
        showConnectionsCount();
        return connection;
    }

    public static void returnConnection(Connection connection) {
        pool.returnConnectionToPool(connection);
        showConnectionsCount();
    }

    public static void showConnectionsCount() {
        System.out.println("In pull " + pool.availableConnections.size() + " connections.");
    }
}
