package daolayer.pool;

import daolayer.exeptions.ConnectionPoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Sergey Solovyov
 */
public class ConnectionPool implements AutoCloseable{

    private BlockingQueue<PooledConnection> availableConnections;
    private BlockingQueue<PooledConnection> usedConnections;
    private static final int DEFAULT_CONN_QUANTITY = 5;
    private String url;
    private String login;
    private String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

    public ConnectionPool(int connsQnt) {

        DBInfo dbInfo = DBInfo.getInstance();
        try {
            Class.forName(dbInfo.getDriver());
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }
        int connectionsQnt;
        if (connsQnt < 5)
            connectionsQnt = DEFAULT_CONN_QUANTITY;
        else connectionsQnt = connsQnt;
        availableConnections = new ArrayBlockingQueue<>(connectionsQnt);
        usedConnections = new ArrayBlockingQueue<>(connectionsQnt);
        this.url = dbInfo.getUrl();
        this.login = dbInfo.getLogin();
        this.password = dbInfo.getPassword();
        for (int i = 0; i < connectionsQnt; i++){
            availableConnections.add(new PooledConnection(getConnection(), this));
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }
        return conn;
    }

    public synchronized PooledConnection retrieve() throws ConnectionPoolException {
        if (availableConnections.isEmpty()) {
            throw new ConnectionPoolException("Pool is empty");
        } else {
            PooledConnection newConn = availableConnections.poll();
            usedConnections.add(newConn);
            return newConn;
        }
    }

    public synchronized void putBack(PooledConnection conn)  {
                 if (conn != null) {
                 usedConnections.remove(conn);
                 availableConnections.add(conn);
        }
    }
    public int getAvailableConnections() {
        return availableConnections.size();
    }

    public int getActiveConnections() {
        return usedConnections.size();
    }


    @Override
    public void close()  {
        int availSize = availableConnections.size();
        try {
            for (int i = 0; i < availSize; i++) {
                availableConnections.poll().close();
            }
            int usedSize = usedConnections.size();
            for (int i = 0; i < usedSize; i++) {
                usedConnections.poll().close();
            }
        }catch (SQLException e){
            LOGGER.info(e.getMessage(), e);
        }

    }
}