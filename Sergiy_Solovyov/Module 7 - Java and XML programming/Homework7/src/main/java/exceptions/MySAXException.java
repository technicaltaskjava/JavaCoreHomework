package exceptions;

/**
 * @author Sergey Solovyov
 */
public class MySAXException extends Exception {

    public MySAXException(String message){
        super(message);
    }

    public MySAXException(String message, Throwable cause) {
        super(message, cause);
    }
}
