package db.exceptions;

public class UpdateException extends Exception {

    public UpdateException(Exception e){
        this.initCause(e);
    }
}
