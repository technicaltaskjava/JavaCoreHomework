package homework.task2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionControllerTest {
	TransactionController controller;

	@Before
	public void setUp() throws Exception {
		controller = new TransactionController();
	}

	@Test
	public void testExecute() {
		controller.execute();
		String expected = "Account list\n" +
				"Account{accountId=1, balance=110}\n" +
				"Account{accountId=2, balance=90}\n" +
				"Account{accountId=3, balance=80}\n" +
				"Account{accountId=4, balance=110}\n" +
				"Account{accountId=5, balance=110}\n";
		assertEquals(expected, controller.getBalance());
	}

	@Test
	public void testGetBalance() {
		String expected = "Account list\n" +
				"Account{accountId=1, balance=100}\n" +
				"Account{accountId=2, balance=100}\n" +
				"Account{accountId=3, balance=100}\n" +
				"Account{accountId=4, balance=100}\n" +
				"Account{accountId=5, balance=100}\n";
		assertEquals(expected, controller.getBalance());
	}
	
}