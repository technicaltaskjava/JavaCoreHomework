package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {
	
	@Test
	public void testLongTimeToString() throws Exception {
		long time = 1350000;
		String expected = "00:22:30";
		String actual = Util.longTimeToString(time);
		assertEquals(expected, actual);
	}

	@Test
	public void testStringTimeToLongWithTwoSeparator() throws Exception {
		String input = "00:05:05";
		long actual = Util.StringTimeToLong(input);
		assertNotNull(actual);
	}

	@Test
	public void testStringTimeToLongWithOneSeparator() throws Exception {
		String input = "05:05";
		long actual = Util.StringTimeToLong(input);
		assertNotNull(actual);
	}

	@Test
	public void testStringTimeToLongWithOutSeparator() throws Exception {
		String input = "05";
		long actual = Util.StringTimeToLong(input);
		assertTrue(actual == 0);
	}
}