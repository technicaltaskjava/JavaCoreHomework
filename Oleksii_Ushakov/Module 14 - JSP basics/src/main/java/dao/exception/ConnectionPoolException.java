package dao.exception;

/**
 * @author Alexey Ushakov
 */
public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
