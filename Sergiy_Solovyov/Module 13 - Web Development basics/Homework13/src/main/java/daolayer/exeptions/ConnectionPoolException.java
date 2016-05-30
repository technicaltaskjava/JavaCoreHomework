package daolayer.exeptions;

/**
 * @author Sergey Solovyov
 */
public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message){
        super(message);
    }
}
