package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 17.04.2016.
 */
public class Transaction {

    private int sender;
    private int recipient;
    private double amount;

    public Transaction(int sender, int recipient, double amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    public int getSender() {
        return sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return  amount;
    }

    @Override
    public String toString() {
        return "Sender: " + sender + ", recipient: " + recipient + ", amount: " + amount;
    }
}
