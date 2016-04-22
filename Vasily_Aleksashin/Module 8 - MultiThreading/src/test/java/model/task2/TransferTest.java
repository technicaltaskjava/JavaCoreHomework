package model.task2;

import exception.ParameterIncorrectException;
import model.task2.entity.Account;
import model.task2.entity.Record;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static utility.Constant.*;

public class TransferTest {
	private Account accountSender;
	private Account accountRecipient;
	private Transfer transfer;

	@Before
	public void setUp() throws ParameterIncorrectException {
		final ConcurrentLinkedQueue<Record> records = new ConcurrentLinkedQueue<>();
		records.offer(new Record(SENDER_ID, RECIPIENT_ID, SUM));
		accountSender = new Account(SENDER_ID);
		accountSender.increase(SUM);
		accountRecipient = new Account(RECIPIENT_ID);
		accountRecipient.increase(SUM);
		final TransactionService serviceMock = mock(TransactionService.class);
		when(serviceMock.getRecords()).thenReturn(records);
		when(serviceMock.getAccount(SENDER_ID)).thenReturn(accountSender);
		when(serviceMock.getAccount(RECIPIENT_ID)).thenReturn(accountRecipient);
		when(serviceMock.decreaseAccount(SENDER_ID, SUM)).thenCallRealMethod();
		when(serviceMock.increaseAccount(RECIPIENT_ID, SUM)).thenCallRealMethod();
		transfer = new Transfer(serviceMock);
	}

	@Test
	public void testRun() {
		transfer.run();
		assertTrue(accountSender.balance() == ZERO_BALANCE && accountRecipient.balance() == SUM * 2);
	}
}