package javase.t02.accounts;

/**
 * Class for storing account data
 * Created by Yury Vislobodsky on 15.04.2016.
 */
public class Account {
    private int id;
    private int balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public void offsetBalance(int offsetSum) {
        this.balance += offsetSum;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account #" + id + " (balance: " + balance + ")";
    }

    @Override
    public int hashCode() {
        return new Integer(id).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return this == object || (object != null && getClass() == object.getClass() && this.hashCode() == object.hashCode());
    }
}
