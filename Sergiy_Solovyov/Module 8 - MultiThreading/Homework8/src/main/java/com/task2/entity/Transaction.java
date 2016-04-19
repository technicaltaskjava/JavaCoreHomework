package com.task2.entity;

import com.task2.parser.TextParser;
import com.task2.exceptions.OverdraftException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergey Solovyov
 */
public class Transaction {

    private String accountTo;
    private String accountFrom;
    private int amount;
    private static final Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

    public Transaction(String accountFrom, String accountTo, int amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public  void runTransaction()  {

        TextParser.accounts.get(accountTo).deposit(amount);
        try {
            TextParser.accounts.get(accountFrom).withdraw(amount);
        } catch (OverdraftException e) {
            LOGGER.info(e.getMessage(), e);
            TextParser.accounts.get(accountTo).rollBack(amount);
        }
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", amount=" + amount +
                '}';
    }
}
