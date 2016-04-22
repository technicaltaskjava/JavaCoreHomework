package model.task2;

import exception.ParameterIncorrectException;
import org.junit.Before;
import org.junit.Test;
import utility.Constant;

import static org.junit.Assert.*;

public class TransactionServiceTest {
	private TransactionService service;

	@Before
	public void setUp() throws ParameterIncorrectException {
		service = new TransactionService(2);
		service.addAccount();

	}

	@Test
	public void testInitWithCount() throws ParameterIncorrectException {
		int count = 0;
		service = new TransactionService(Constant.DEFAULT_ACCOUNT_COUNT);
		for (int index = 1; index <= Constant.DEFAULT_ACCOUNT_COUNT; index++) {
			if (service.getAccount(index) != null) {
				count++;
			}
		}
		assertTrue(count == Constant.DEFAULT_ACCOUNT_COUNT);
	}

	@Test
	public void testAddAccount() throws ParameterIncorrectException {
		assertTrue(service.addAccount());
	}

	@Test
	public void testAddAccountNotExist() throws ParameterIncorrectException {
		assertNull(service.getAccount(Integer.MAX_VALUE));
	}

	@Test
	public void testGetAccount() throws ParameterIncorrectException {
		assertNotNull(service.getAccount(Constant.SENDER_ID));
	}

	@Test
	public void testGetBalanceZero() throws ParameterIncorrectException {
		assertTrue(service.getBalance(Constant.SENDER_ID) == 100);
	}

	@Test
	public void testGetAccountPool() {
		assertTrue(service.getAccountPool().size() == 3);

	}
}