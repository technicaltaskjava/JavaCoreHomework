package javase.t04.demo;

import javase.t04.account.Account;
import javase.t04.concrete.AtmAccountService;
import javase.t04.concrete.CashBoxAccountService;
import javase.t04.concrete.TerminalAccountService;

/**
 * Demo class to verify processing with account
 * Created by Yury Vislobodsky on 23.04.2016.
 */
public class AccountDemo {
    private AccountDemo() {}

    public static void main(String[] args) {
        Account account = new Account();

        account.setAccountService(new AtmAccountService());
        account.executeDeposit(200);
        account.executeWithdraw(100);

        account.setAccountService(new TerminalAccountService());
        account.executeDeposit(200);
        account.executeWithdraw(100);

        account.setAccountService(new CashBoxAccountService());
        account.executeDeposit(200);
        account.executeWithdraw(100);
    }
}
