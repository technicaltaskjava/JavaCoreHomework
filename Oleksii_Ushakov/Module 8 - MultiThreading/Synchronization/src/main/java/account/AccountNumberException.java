package account;

/**
 * @author Alexey Ushakov
 */
public class AccountNumberException extends IllegalArgumentException {
    public AccountNumberException(String message) {
        super(message);
    }
}
