package moviecollection.exceptions;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class MovieCollectionSaveException extends IOException {

    public MovieCollectionSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
