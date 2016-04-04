package test;

import t01.model.annotation.Test;

import static t01.model.assertion.Assert.*;

public class TestTest {
	@Test
	public void testAssertionEquals() {
		String expected = "test";
		String actual = "test";
		assertEquals(expected, actual);
	}

	@Test
	public void testFail() {
		fail();
	}

	@Test(ignore = true)
	public void testAssertionIgnore() {
		String expected = "test";
		String actual = "test1";
		assertEquals(expected, actual);
	}

	@Test(expected = NullPointerException.class)
	public void testAssertionNotEquals() {
		String testNull = null;
		//noinspection TestConditions
		testNull.length();
	}

	@Test
	public void testAssertionTrue() {
		assertTrue(true);
	}

	@Test
	public void testAssertionFalse() {
		assertFalse(false);
	}

	@Test
	public void testAssertionNull() {
		assertNull(null);
	}

	@Test
	public void testAssertionNotNull() {
		assertNotNull(new Object());
	}
}
