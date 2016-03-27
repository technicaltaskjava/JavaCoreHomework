package htmlimagesearch.exceptions;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class NotReadableException extends IOException {
    public NotReadableException(String message) {
        super(message);
    }
}
