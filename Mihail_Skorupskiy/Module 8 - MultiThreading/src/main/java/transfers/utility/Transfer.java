package transfers.utility;

import transfers.exceptions.NegativeAmount;
import transfers.exceptions.NegativeBalance;

public class Transfer {
    private Account source;
    private Account destination;
    private long amount;

    public Transfer(Account source, Account destination, long amount) throws NegativeAmount {
        setAmount(amount);
        this.source = source;
        this.destination = destination;
    }

    public void commit() throws NegativeBalance {
        source.withdraw(amount);
        destination.deposit(amount);
    }

    private void setAmount(long amount) throws NegativeAmount {
        if (amount >= 0) {
            this.amount = amount;
        } else {
            throw new NegativeAmount();
        }
    }

    @Override
    public String toString(){
        return "Transfer of " + amount + " units from " + source.getNumber() + " to " + destination.getNumber();
    }
}
