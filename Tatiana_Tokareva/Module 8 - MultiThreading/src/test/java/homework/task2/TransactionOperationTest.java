package homework.task2;

import homework.util.Constant;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;


public class TransactionOperationTest {
	TransactionOperation transactionOperation = new TransactionOperation();

	@Test
	public void testParsFile() throws FileNotFoundException {
		transactionOperation.parsFile(Constant.TEST_FILE_TXT);
		final String expected = "Transaction{sender=1, recipient=2, amount=5}";
		assertEquals(expected, transactionOperation.pollTransaction().toString());

	}

	@Test
	public void testGetStartAccount() {
		final String expected = "Account{accountId=1, balance=100}";
		assertEquals(expected, transactionOperation.getAccount(1).toString());
	}


}