package dao.exeption;

/**
 * @author Alexey Ushakov
 */
public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
