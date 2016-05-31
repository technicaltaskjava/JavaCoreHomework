package dao.exception;

/**
 * @author Alexey Ushakov
 */
public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
