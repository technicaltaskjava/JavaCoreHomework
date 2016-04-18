package javase.t02.transactions;

/**
 * Class to describe transaction data
 * Created by Yury Vislobodsky on 15.04.2016.
 */
public class Transaction {
    private int accountIdFrom;
    private int accountIdTo;
    private int sum;

    public Transaction(int accountIdFrom, int accountIdTo, int sum) {
        this.accountIdFrom = accountIdFrom;
        this.accountIdTo = accountIdTo;
        this.sum = sum;
    }

    public int getAccountIdFrom() {
        return accountIdFrom;
    }

    public int getAccountIdTo() {
        return accountIdTo;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "Transaction [" + accountIdFrom + "] --> [" + accountIdTo + "] : " + sum;
    }
}
