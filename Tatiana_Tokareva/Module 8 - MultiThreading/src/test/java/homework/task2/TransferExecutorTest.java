package homework.task2;

import homework.util.Constant;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TransferExecutorTest {
	private TransactionOperation operation;

	@Before
	public void setUp() throws Exception {
		operation = new TransactionOperation();
		operation.parsFile(Constant.TEST_FILE_TXT);
		TransferExecutor executor = new TransferExecutor(operation);
		executor.run();

	}

	@Test
	public void testRun() {

		assertNull(operation.pollTransaction());

	}

	@Test
	public void testBalance() {
		String expected = "[Account{accountId=1, balance=95}," +
				" Account{accountId=2, balance=105}, " +
				"Account{accountId=3, balance=100}, " +
				"Account{accountId=4, balance=100}," +
				" Account{accountId=5, balance=100}]";
		assertEquals(expected, operation.getAccounts().toString());
	}
}