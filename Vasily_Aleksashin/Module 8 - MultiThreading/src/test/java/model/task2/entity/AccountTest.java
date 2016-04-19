package model.task2.entity;

import org.junit.Before;
import org.junit.Test;
import utility.Constant;

import static org.junit.Assert.*;

public class AccountTest {
	private Account account;

	@Before
	public void setUp() {
		account = new Account(Constant.SENDER_ID);
	}

	@Test
	public void testGetId() {
		assertTrue(account.getId() == Constant.SENDER_ID);
	}

	@Test
	public void testBalance() {
		assertTrue(account.balance() == 0);
	}

	@Test
	public void testIncrease() {
		account.increase(Constant.SUM);
		assertTrue(account.balance() == Constant.SUM);
	}

	@Test
	public void testDecrease() {
		account.increase(Constant.SUM);
		account.decrease(Constant.SUM);
		assertTrue(account.balance() == 0);
	}

	@Test
	public void testDecreaseMoreThenBalance() {
		account.increase(Constant.SUM);
		account.decrease(Constant.SUM + Constant.SUM);
		assertTrue(account.balance() == Constant.SUM);
	}

	@Test
	public void testEqualsReflexive() {
		assertTrue(account.equals(account));
	}

	@Test
	public void testEqualsNewAccountWithSameId() {
		assertTrue(account.equals(new Account(Constant.SENDER_ID)));
	}

	@Test
	public void testEqualsNewAccountWithDiffId() {
		assertFalse(account.equals(new Account(Constant.SENDER_ID + Constant.SENDER_ID)));
	}

	@Test
	public void testEqualsNull() {
		assertFalse(account.equals(null));
	}

	@Test
	public void testEquals() {
		assertFalse(account.equals(new Object()));
	}

	@Test
	public void testHashCode() {
		assertTrue(account.hashCode() == Constant.SENDER_ID);
	}

	@Test
	public void testToString() {
		final String expected = "Account: id=1, balance=0";
		assertEquals(expected, account.toString());
	}
	
}