package db.exceptions;

public class SearchException extends Exception {

    public SearchException(Throwable e) {
        this.initCause(e);
    }
}
