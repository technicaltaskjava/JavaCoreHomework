package transaction.exception;

import account.Account;

/**
 * @author Alexey Ushakov
 */
public class LoopTransactionException extends IllegalArgumentException {
    public LoopTransactionException(Account sender) {
        super("Sender and recipient ID are equals " + sender.getId());
    }
}
