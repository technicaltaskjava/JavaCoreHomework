package javase.t04.concrete;

import javase.t04.strategy.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AccountService interface implementation for Bank Terminal
 * Created by Yury Vislobodsky on 23.04.2016.
 */
public class TerminalAccountService implements AccountService {
    private static Logger logger = LoggerFactory.getLogger(TerminalAccountService.class);

    @Override
    public double deposit(double balance, double sum) {
        double newBalance = balance + sum;
        logger.info(String.format("Deposit %.2f - DONE, balance: %.2f", sum, newBalance));
        return newBalance;
    }

    @Override
    public double withdraw(double balance, double sum) {
        logger.warn(String.format("Withdraw %.2f - REJECTED (not supported), balance: %.2f", sum, balance));
        return balance;
    }
}
