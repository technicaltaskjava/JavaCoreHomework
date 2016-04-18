package transfers.exceptions;

public class InvalidAccount extends TransferException {

    //This was initially called "InvalidAccountException", but Sonar thinks that
    //this class is not an exception, and shouldn't be called "...Exception",
    //even though it extends a class that extends Exception.

    @Override
    public String toString(){
        return "Specified account doesn't exist.";
    }
}
