package model.t02;

import exeption.ParameterIsNullException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class CrazyLoggerTest {
	private static final String TEST_MESSAGE = "TEST MESSAGE";
	private static final String NO_MESSAGE = "no message";
	private static CrazyLogger logger = CrazyLogger.getLogger(CrazyLoggerTest.class);
	long now;
	String timeStamp;

	@Before
	public void setUp() {
		now = System.currentTimeMillis();
		timeStamp = new SimpleDateFormat("dd-MM-yyyy : HH-mm").format(new Date(now));
		logger.log(TEST_MESSAGE);
	}

	@After
	public void tearDown() {
		logger.clear();
	}

	@Test
	public void testLogMessage() {
		logger.log(TEST_MESSAGE);
		assertTrue(logger.toString().contains(TEST_MESSAGE));
	}

	@Test
	public void testLogMessageNull() {
		logger.log(null);
		assertTrue(logger.toString().contains(NO_MESSAGE));
	}

	@Test
	public void testLogLevelIntMessage() {
		logger.log(1, TEST_MESSAGE);
		assertTrue(logger.toString().contains("DEBUG"));
	}

	@Test
	public void testLogLevelNullMessage() {
		logger.clear();
		logger.log(null, TEST_MESSAGE);
		assertFalse(logger.toString().contains("DEBUG") || logger.toString().contains("ERROR") || logger.toString().contains("INFO"));
	}

	@Test
	public void testLogLevelMessage() {
		logger.log(Level.INFO, TEST_MESSAGE);
		assertTrue(logger.toString().contains("[INFO ]"));
	}

	@Test
	public void testFindMessage() throws ParameterIsNullException {
		logger.log(TEST_MESSAGE);
		logger.log(TEST_MESSAGE + "33");
		logger.log(TEST_MESSAGE + "2");
		logger.log(TEST_MESSAGE + "3");
		logger.log(TEST_MESSAGE + "42");

		String expected = timeStamp + " - [INFO ] - model.t02.CrazyLoggerTest - TEST MESSAGE33\n" +
				timeStamp + " - [INFO ] - model.t02.CrazyLoggerTest - TEST MESSAGE3\n";

		assertEquals(expected, logger.findLog("3"));

	}

	@Test(expected = ParameterIsNullException.class)
	public void testFindMessageIsNull() throws ParameterIsNullException {
		logger.findLog((String[]) null);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testFindDateIsNull() throws ParameterIsNullException {
		logger.findLog((Date) null);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testFindDatesIsNull() throws ParameterIsNullException {
		logger.findLog(null, null);
	}

	@Test
	public void testFindMessageAsArray() throws ParameterIsNullException {
		logger.log(TEST_MESSAGE);
		logger.log(TEST_MESSAGE + "33");
		logger.log(TEST_MESSAGE + "23");
		logger.log(TEST_MESSAGE + "3");
		logger.log(TEST_MESSAGE + "4");

		String expected = timeStamp + " - [INFO ] - model.t02.CrazyLoggerTest - TEST MESSAGE33\n" +
				timeStamp + " - [INFO ] - model.t02.CrazyLoggerTest - TEST MESSAGE23\n" +
				timeStamp + " - [INFO ] - model.t02.CrazyLoggerTest - TEST MESSAGE3\n";

		assertEquals(expected, logger.findLog("EST", "3"));

	}

	@Test
	public void testFindDate() throws ParameterIsNullException {
		String expected = timeStamp + " - [INFO ] - model.t02.CrazyLoggerTest - TEST MESSAGE\n";
		assertEquals(expected, logger.findLog(now));
	}


}