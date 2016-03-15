package task1.exceptions;

import java.io.IOException;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 09.03.2016
 */
public class InvalidDriveInputException  extends Exception {

    public InvalidDriveInputException(String message){
        super(message);
    }
}
