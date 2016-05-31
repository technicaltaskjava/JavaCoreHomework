package dao.exception;

/**
 * @author Alexey Ushakov
 */
public class AddCookieException extends RuntimeException {
    public AddCookieException(String message) {
        super(message);
    }

    public AddCookieException(String message, Throwable cause) {
        super(message, cause);
    }
}
