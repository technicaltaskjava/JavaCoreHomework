package dao.exception;

/**
 * @author Alexey Ushakov
 */
public class NoSuchMetadataException extends RuntimeException {
    public NoSuchMetadataException(String message) {
        super(message);
    }

    public NoSuchMetadataException(String message, Throwable cause) {
        super(message, cause);
    }
}
