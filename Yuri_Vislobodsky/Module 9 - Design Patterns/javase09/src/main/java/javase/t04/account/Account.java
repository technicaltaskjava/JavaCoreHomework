package javase.t04.account;

import javase.t04.strategy.AccountService;

/**
 * Account class contains total sum and applies services
 * with Strategy pattern
 * Created by Yury Vislobodsky on 23.04.2016.
 */
public class Account {
    private double balance;
    private AccountService accountService;

    public double getBalance() {
        return balance;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void executeDeposit(double sum) {
        balance = accountService.deposit(balance, sum);
    }

    public void executeWithdraw(double sum) {
        balance = accountService.withdraw(balance, sum);
    }
}
