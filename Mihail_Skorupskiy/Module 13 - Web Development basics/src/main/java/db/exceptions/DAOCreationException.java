package db.exceptions;

public class DAOCreationException extends Exception {

    public DAOCreationException(Exception e){
        this.initCause(e);
    }
}
