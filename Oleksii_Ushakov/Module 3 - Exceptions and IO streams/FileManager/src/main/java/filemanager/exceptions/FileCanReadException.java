package filemanager.exceptions;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class FileCanReadException extends IOException {
    public FileCanReadException(String message) {
        super(message);
    }
}
