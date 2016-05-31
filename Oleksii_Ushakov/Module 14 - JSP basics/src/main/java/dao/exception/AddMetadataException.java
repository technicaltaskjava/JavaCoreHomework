package dao.exception;

/**
 * @author Alexey Ushakov
 */
public class AddMetadataException extends RuntimeException {
    public AddMetadataException(String message) {
        super(message);
    }

    public AddMetadataException(String message, Throwable cause) {
        super(message, cause);
    }
}
