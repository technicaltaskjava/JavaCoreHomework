package htmlimagesearch.exceptions;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class NotFileException extends IOException {
    public NotFileException(String message) {
        super(message);
    }
}
