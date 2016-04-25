package javase.t04.strategy;

/**
 * Common Account service interface for two operations:
 * deposit and withdraw
 * Created by Yury Vislobodsky on 23.04.2016.
 */
public interface AccountService {

    // add cash sum to account
    public double deposit(double balance, double sum);

    // withdraw cash sum from account
    public double withdraw(double balance, double sum);

}
