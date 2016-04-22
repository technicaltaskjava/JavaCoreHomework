package task2;

/**
 * Created by Oleg on 20.04.2016.
 */
public class Account {

    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount){
        balance = balance + amount;
    }

    public void withdraw(int amount){
        balance = balance - amount;
    }

    public void showAll(){
        System.out.println(balance);
    }

}
