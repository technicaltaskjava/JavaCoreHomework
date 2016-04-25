package javase.t04.concrete;

import javase.t04.strategy.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AccountService interface implementation for Bank CashBox
 * Created by Yury Vislobodsky on 23.04.2016.
 */
public class CashBoxAccountService implements AccountService {
    private static Logger logger = LoggerFactory.getLogger(CashBoxAccountService.class);

    @Override
    public double deposit(double balance, double sum) {
        double newBalance = balance + sum;
        logger.info(String.format("Deposit %.2f - DONE, balance: %.2f", sum, newBalance));
        return newBalance;
    }

    @Override
    public double withdraw(double balance, double sum) {
        if (sum > balance) {
            logger.warn(String.format("Withdraw %.2f - REJECTED (not enough money), balance: %.2f", sum, balance));
            return balance;
        }
        double newBalance = balance - sum;
        logger.info(String.format("Withdraw %.2f - DONE, balance: %.2f", sum, newBalance));
        return newBalance;
    }
}