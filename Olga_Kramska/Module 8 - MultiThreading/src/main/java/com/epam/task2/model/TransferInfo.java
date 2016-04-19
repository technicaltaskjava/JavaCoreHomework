package com.epam.task2.model;

import java.math.BigDecimal;

/**
 * Created by Olga Kramska on 17-Apr-16.
 */
public class TransferInfo {
    private int sourceAccountNumber;
    private int targetAccountNumber;
    private BigDecimal amount;

    public TransferInfo(int sourceAccountNumber, int targetAccountNumber, BigDecimal amount) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
        this.amount = amount;
    }

    public int getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public int getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "TransferInfo{" +
                "sourceAccountNumber=" + sourceAccountNumber +
                ", targetAccountNumber=" + targetAccountNumber +
                ", amount=" + amount +
                '}';
    }
}
