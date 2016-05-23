package javase.connectionpool;

/**
 * The only own exception for Connection Pool
 * Created by Yury Vislobodsky on 05.05.2016.
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }
}
