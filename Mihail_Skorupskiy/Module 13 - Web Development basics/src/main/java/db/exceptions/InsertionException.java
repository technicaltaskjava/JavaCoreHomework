package db.exceptions;

public class InsertionException extends Exception {

    public InsertionException(Exception e){
        this.initCause(e);
    }
}
