package javase.t01.demo;

import javase.common.connectionpool.ConnectionPool;
import javase.common.connectionpool.ConnectionPoolException;
import javase.t01.metadata.*;

import java.sql.*;

/**
 * Demo class shows table METADATA handling
 * with SQL and ResultSet methods
 * Created by Yury Vislobodsky on 04.05.2016.
 */
public class MetadataDemo {
    private MetadataDemo() {
    }

    public static void datasourceDemo(MetadataBase metadata) throws SQLException {
        metadata.dropTable();
        metadata.createTable();

        metadata.insert(2, 4);
        metadata.insert(3, 2);
        metadata.insert(5, 4);
        metadata.insert(4, 3);
        metadata.insert(2, 1);
        metadata.insert(4, 5);
        metadata.insert(1, 2);
        metadata.insert(3, 1);
        metadata.insert(2, 3);
        metadata.insert(5, 2);
        System.out.print("\nAfter insert:");
        metadata.viewTable();

        metadata.update(2, 3, false);
        metadata.update(1, 5, false);
        System.out.print("\nAfter update:");
        metadata.viewTable();

        metadata.delete(3);
        metadata.delete(1);
        System.out.print("\nAfter delete:");
        metadata.viewTable();
    }


    public static void main(String[] args) throws ConnectionPoolException, SQLException {
        ConnectionPool pool = new ConnectionPool();
        Connection connection = pool.getConnection();

        System.out.print("\nDemonstration of METADATA handling by SQL methods");
        datasourceDemo(new MetadataSQL(connection));

        System.out.print("\nDemonstration of METADATA handling by ResultSet methods");
        datasourceDemo(new MetadataRS(connection));

        pool.retrieveConnection(connection);
        pool.closeConnectionPool();
    }
}
