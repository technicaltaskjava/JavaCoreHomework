package task2;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    static ConnectionPool pool = new ConnectionPool();

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = pool.getConnectionFromPool();
        return connection;
    }

    public static void returnConnection(Connection connection) {
        pool.returnConnectionToPool(connection);
    }
}
