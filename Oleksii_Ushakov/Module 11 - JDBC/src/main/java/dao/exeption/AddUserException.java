package dao.exeption;

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
