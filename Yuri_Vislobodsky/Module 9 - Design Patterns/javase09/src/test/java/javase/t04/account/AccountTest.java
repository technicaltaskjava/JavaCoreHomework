package javase.t04.account;

import javase.t04.concrete.AtmAccountService;
import javase.t04.concrete.CashBoxAccountService;
import javase.t04.concrete.TerminalAccountService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for Account class with Strategy pattern
 * Checks account balance after deposit and withdraw processing
 * Created by Yury on 22.04.2016.
 */
public class AccountTest {
    private Account account = new Account();

    @Test
    public void testAccount() {
        account.setAccountService(new AtmAccountService());
        account.executeDeposit(200);
        account.executeWithdraw(100);

        account.setAccountService(new TerminalAccountService());
        account.executeDeposit(200);
        account.executeWithdraw(100);

        account.setAccountService(new CashBoxAccountService());
        account.executeDeposit(200);
        account.executeWithdraw(100);

        assertEquals("error in balance", 300, Math.round(account.getBalance()));
    }
}