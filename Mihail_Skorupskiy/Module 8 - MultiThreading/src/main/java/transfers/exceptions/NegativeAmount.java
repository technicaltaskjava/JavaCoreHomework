package transfers.exceptions;

public class NegativeAmount extends TransferException {

    //This was initially called "NegativeAmountException", but Sonar thinks that
    //this class is not an exception, and shouldn't be called "...Exception",
    //even though it extends a class that extends Exception.

    @Override
    public String toString(){
        return "Transfer amount can't be negative.";
    }
}
