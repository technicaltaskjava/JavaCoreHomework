package account;

/**
 * @author Alexey Ushakov
 */
public class AccountNotEnoughMoney extends ArithmeticException {
    public AccountNotEnoughMoney(Account account) {
        super(String.format("Not enough money on the account %1d", account.getId()));
    }
}
