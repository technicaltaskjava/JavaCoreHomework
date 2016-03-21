package filemanager.exceptions;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class FileNotCreatedException extends IOException {
    public FileNotCreatedException(String message) {
        super(message);
    }
}
