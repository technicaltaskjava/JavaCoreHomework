package javase.t02.transactions;

/**
 * Common exception raising when some troubles take place with Transactions Parser
 * Created by Yury Vislobodsky on 10.04.2016.
 */
public class TransactionsParserException extends Exception {
    private final Exception exception;

    public TransactionsParserException(Exception exception) {
        super(exception);
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "Transactions Parser exception: " + exception.getMessage();
    }
}

