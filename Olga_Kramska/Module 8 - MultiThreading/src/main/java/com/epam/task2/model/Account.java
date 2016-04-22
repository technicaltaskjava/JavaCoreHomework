package com.epam.task2.model;

import java.math.BigDecimal;

/**
 * Created by Olga Kramska on 17-Apr-16.
 */
public class Account {
    private int number;
    private BigDecimal value;

    public Account(int number, BigDecimal value) {
        this.number = number;
        this.value = value;
    }

    public Account(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Account{" +
                "value = " + value +
                '}';
    }
}
