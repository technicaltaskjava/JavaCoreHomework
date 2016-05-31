package dao.exception;

/**
 * @author Alexey Ushakov
 */
public class AddUserException extends RuntimeException {
    public AddUserException(String message) {
        super(message);
    }

    public AddUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
