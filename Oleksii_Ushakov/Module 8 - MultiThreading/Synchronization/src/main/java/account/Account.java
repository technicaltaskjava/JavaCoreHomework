package account;

/**
 * @author Alexey Ushakov
 */
public class Account {
    private final long id;
    private volatile int balance = 5000; /*why not, it's just for example*/

    public Account(int id) {
        if (id < 0) {
            throw new AccountNumberException("Wrong account number ".concat(String.valueOf(id)));
        } else {
            this.id = id;
        }
    }

    public Account(String cardNumber) {
        String number = cardNumber.replaceAll("\\s", "");

        if (number.matches("[\\d]{16}")) {
            this.id = Long.valueOf(number);
        } else {
            throw new AccountNumberException("Wrong account number ".concat(number));
        }
    }

    public long getId() {
        return id;
    }

    public void addCash(int cash) {
        this.balance += cash;
    }

    public void takeCash(int cash) {
        if ((this.balance - cash) < 0) {
            throw new AccountNotEnoughMoney(this);
        }

        this.balance -= cash;
    }

    @Override
    public String toString() {
        return String.format("%016d", id);
    }

}
