package com.epam.task2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yuriy Krishtop on 18.04.2016.
 */
public class TransactionTest {

    @Test
    public void transactionTest() {
        Transaction transaction = new Transaction(78, 34, 1500);
        assertEquals(78, transaction.getSender());
        assertEquals(34, transaction.getRecipient());
        assertEquals(0, Double.compare(1500, transaction.getAmount()));
    }
}
