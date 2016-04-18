package transfers.exceptions;

public class NegativeBalance extends TransferException {

    //This was initially called "NegativeBalanceException", but Sonar thinks that
    //this class is not an exception, and shouldn't be called "...Exception",
    //even though it extends a class that extends Exception.

    @Override
    public String toString(){
        return "Account doesn't have enough units to complete transfer.";
    }
}
