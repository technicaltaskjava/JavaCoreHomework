package dao.exeption;

/**
 * @author Alexey Ushakov
 */
public class NoSuchCookieException extends RuntimeException {
    public NoSuchCookieException(String message) {
        super(message);
    }

    public NoSuchCookieException(String message, Throwable cause) {
        super(message, cause);
    }
}

