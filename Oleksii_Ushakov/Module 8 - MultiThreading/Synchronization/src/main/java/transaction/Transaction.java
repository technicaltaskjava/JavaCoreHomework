package transaction;

import account.Account;
import org.apache.log4j.Logger;
import transaction.exception.LoopTransactionException;

/**
 * @author Alexey Ushakov
 */
public class Transaction implements Runnable {
    public static final Logger logger = Logger.getLogger(Transaction.class);
    private final Account sender;
    private final Account recipient;
    private final int value;

    public Transaction(Account sender, Account recipient, int value) {
        this.sender = sender;
        this.recipient = recipient;
        this.value = value;
    }


    @Override
    public void run() {
        logger.info(String.format(" Run transaction: %s ==[%d]=> %s", sender, value, recipient));
        if (sender.equals(recipient)) {
            throw new LoopTransactionException(sender);
        }

        Account accFirst;
        Account accSecond;

        if (sender.hashCode() < recipient.hashCode()) {
            accFirst = sender;
            accSecond = recipient;
        } else {
            accFirst = recipient;
            accSecond = sender;
        }

        synchronized (accFirst) {
            synchronized (accSecond) {
                sender.takeCash(value);
                recipient.addCash(value);
            }
        }

        logger.info(String.format("Done transaction: %s ==[%d]=> %s", sender, value, recipient));
    }

    @Override
    public String toString() {
        return String.format("From %1s to %2s transmit %3d", sender, recipient, value);
    }
}
