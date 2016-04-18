package javase.t02.transactions;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.concurrent.BlockingQueue;

/**
 * Test for Transactions Parser class
 * Checks count and sums of transactions, included in xml-file
 * Created by Yury Vislobodsky on 18.04.2016.
 */
public class TransactionsParserTest {
    BlockingQueue<Transaction> transactions;

    @Test
    public void testTransactionsParserExecute() throws InterruptedException, TransactionsParserException {
        transactions = TransactionsParser.createFromXml("src/main/resources/transactions.xml");

        assertEquals("error in transactions count", 85, transactions.size());

        assertEquals("error in transactions sums", 1380, getTransactionsSum());
    }

    private int getTransactionsSum() {
        int total = 0; // Sonar requires this initialization
        for (Transaction transaction : transactions) {
            total += transaction.getSum();
        }
        return total;
    }
}