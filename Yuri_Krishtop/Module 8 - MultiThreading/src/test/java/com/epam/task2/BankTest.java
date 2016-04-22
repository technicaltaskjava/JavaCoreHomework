package com.epam.task2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yuriy Krishtop on 17.04.2016.
 */
public class BankTest {

    @Test
    public void bankTransactionTest() {
        Bank bank = new Bank(10, 500);
        bank.setTransactionsFileName("src/main/resources/test.txt");
        assertEquals(91, bank.createTransaction(bank.readTextFromFile()).get(0).getSender());
        assertEquals(65, bank.createTransaction(bank.readTextFromFile()).get(0).getRecipient());
    }

    @Test
    public void bankAccountsTest() {
        Bank bank = new Bank(100, 1000);
        assertEquals(100, bank.getAccounts().size());
        assertEquals(0, Double.compare(1000, bank.getAccounts().get(0).getBalance()));
        assertEquals(0, Double.compare(100000, bank.getTotalBalance()));
    }
}
