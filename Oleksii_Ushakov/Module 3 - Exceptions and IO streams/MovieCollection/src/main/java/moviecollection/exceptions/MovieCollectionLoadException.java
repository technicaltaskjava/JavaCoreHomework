package moviecollection.exceptions;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class MovieCollectionLoadException extends IOException {

    public MovieCollectionLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
