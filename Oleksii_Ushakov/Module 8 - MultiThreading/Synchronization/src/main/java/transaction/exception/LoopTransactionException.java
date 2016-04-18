package transaction.exception;

import account.Account;
import org.apache.log4j.Logger;

/**
 * @author Alexey Ushakov
 */
public class LoopTransactionException extends IllegalArgumentException {
    public static final Logger logger = Logger.getLogger(LoopTransactionException.class);

    public LoopTransactionException(Account sender) {
        super("Sender and recipient ID are equals " + sender.getId());
//        logger.error("Sender and recipient ID are equals " + sender.getId());
    }
}
