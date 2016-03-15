package propertiesfilereader.exceptions;

/**
 * @author Alexey Ushakov
 */
public class PropertiesFileNotFoundException extends PropertiesFileException {

    public PropertiesFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesFileNotFoundException(String message) {
        super(message);
    }
}
