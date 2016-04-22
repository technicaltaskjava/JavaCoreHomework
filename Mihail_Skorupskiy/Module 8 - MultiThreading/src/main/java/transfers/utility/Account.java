package transfers.utility;

import transfers.exceptions.NegativeBalance;

public class Account {
    private long number;
    private long balance;

    public Account(long number, long balance) throws NegativeBalance {
        setBalance(balance);
        setNumber(number);
    }

    public long getNumber() {
        return number;
    }

    private void setNumber(long number) {
        this.number = number;
    }

    private void setBalance(long balance) throws NegativeBalance {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new NegativeBalance();
        }
    }

    void deposit(long amount){
        balance += amount;
    }

    void withdraw(long amount) throws NegativeBalance {
        if (balance - amount >= 0){
            balance -= amount;
        } else {
            throw new NegativeBalance();
        }
    }

    @Override
    public String toString(){
        return "Account #" + number + ", balance: " + balance;
    }

    @Override
    public int hashCode(){
        return (int)number;
    }

    @Override
    public boolean equals(Object next){
        if (next == null){
            return false;
        }
        if (next.getClass() != this.getClass()){
            return false;
        } else {
            Account temp = (Account) next;
            return temp.number!=0 && this.number == temp.number;
        }
    }
}
