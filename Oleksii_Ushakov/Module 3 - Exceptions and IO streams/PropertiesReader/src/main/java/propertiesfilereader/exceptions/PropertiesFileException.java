package propertiesfilereader.exceptions;

import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class PropertiesFileException extends IOException {

    public PropertiesFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesFileException(String message) {
        super(message);
    }
}
