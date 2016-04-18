package account;

import org.apache.log4j.Logger;

/**
 * @author Alexey Ushakov
 */
public class AccountNotEnoughMoney extends ArithmeticException {
    public static final Logger logger = Logger.getLogger(AccountNotEnoughMoney.class);

    public AccountNotEnoughMoney(Account account, int cash) {
        super(String.format("Not enough money on the account %1d", account.getId()));
//
//        StringBuilder message = new StringBuilder("Not enough money on the account " + account.getId());
//        message.append("\n       Account balance: ").append(account.getBalance());
//        message.append("\n       Transfer amount: ").append(cash);
//
//        logger.error(message);
    }
}
