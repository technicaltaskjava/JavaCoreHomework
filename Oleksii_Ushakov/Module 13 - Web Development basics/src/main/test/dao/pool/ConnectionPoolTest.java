package dao.pool;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexey Ushakov
 */

class User implements Runnable {
    private static final Logger logger = Logger.getLogger(User.class);
    private Connection connection;
    private long timeOut;

    User(Connection connection, long timeOut) {
        this.connection = connection;
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        try {
            connection.clearWarnings();
            Thread.sleep(timeOut);
            connection.close();
        } catch (InterruptedException | SQLException e) {
            logger.error(e);
        }
    }
}

public class ConnectionPoolTest {
    private ConnectionPool pool = new ConnectionPool("jdbc:h2:tcp://localhost/~/test", "sa", "");

    @Test
    public void testGetConnection() throws InterruptedException {
        Thread hardWorker = new Thread(new User(pool.getConnection(), 10000));
        hardWorker.start();

        Connection firstUserConnection = pool.getConnection();
        Connection lastUserConnection = null;

        Thread lazyWorker = new Thread(new User(firstUserConnection, 1000));/* User 6 must take this connection */
        lazyWorker.start();


        for (int i = 2; i < pool.size() + 1; i++) {
            lastUserConnection = pool.getConnection();
            Thread thread = new Thread(new User(lastUserConnection, 3000));
            thread.start();
        }

        assertEquals(firstUserConnection, lastUserConnection);
    }
}