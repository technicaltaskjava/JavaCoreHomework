package dao.exeption;

/**
 * @author Alexey Ushakov
 */
public class UpdateCookieException extends RuntimeException {
    public UpdateCookieException(String message) {
        super(message);
    }

    public UpdateCookieException(String message, Throwable cause) {
        super(message, cause);
    }
}
