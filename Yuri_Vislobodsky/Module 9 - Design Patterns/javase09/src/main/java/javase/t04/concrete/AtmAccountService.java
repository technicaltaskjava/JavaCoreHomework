package javase.t04.concrete;

import javase.t04.strategy.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AccountService interface implementation for ATM machine
 * Created by Yury Vislobodsky on 23.04.2016.
 */
public class AtmAccountService implements AccountService {
    private static Logger logger = LoggerFactory.getLogger(AtmAccountService.class);

    @Override
    public double deposit(double balance, double sum) {
        logger.warn(String.format("Deposit %.2f - REJECTED (not supported), balance: %.2f", sum, balance));
        return balance;
    }

    @Override
    public double withdraw(double balance, double sum) {
        final double commission = 0.01;
        double sumWithCommission = Math.round(sum * (1 + commission) * 100) / 100;
        if (sumWithCommission > balance) {
            logger.warn(String.format("Withdraw %.2f (incl. commission) - REJECTED (not enough money), balance: %.2f",
                    sumWithCommission, balance));
            return balance;
        }
        double newBalance =  balance - sumWithCommission;
        logger.info(String.format("Withdraw %.2f (incl. commission) - DONE, balance: %.2f",
                sumWithCommission, newBalance));
        return newBalance;
    }
}
