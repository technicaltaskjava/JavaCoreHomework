package transfers;

import transfers.exceptions.InvalidAccount;
import transfers.exceptions.NegativeBalance;
import transfers.utility.Account;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountManager {

    private static Logger logger = Logger.getLogger("Accounts");
    private static HashSet<Account> accounts = new HashSet<>();

    private AccountManager(){}

    public static void initialize(){
        for (int i = 0; i < 9; i++){
            createAccount((long)i + 1, (long)(Math.random()*10000));
        }
    }

    private static void createAccount(long account, long balance){
        try {
            accounts.add(new Account(account, balance));
        } catch (NegativeBalance e){
            logger.log(Level.WARNING, "Account was not created.", e);
        }
    }

    public static Account getAccount(long number) throws InvalidAccount {
        Account temp = null;
        for (Account current : accounts) {
            if (current.getNumber() == number){
                temp = current;
                break;
            }
        }
        if (temp != null) {
            return temp;
        } else {
            throw new InvalidAccount();
        }
    }

    public static void show(){
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
