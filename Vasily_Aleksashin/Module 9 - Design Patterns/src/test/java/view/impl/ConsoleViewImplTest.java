package view.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.View;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class ConsoleViewImplTest {
	private final ConsoleMock console = new ConsoleMock();
	private View view;

	@Before
	public void setup() {
		view = new ConsoleViewImpl();
	}

	@After
	public void tearDown() {
		view.close();
	}

	@Test
	public void testPrint() throws UnsupportedEncodingException {
		view.print("TEST");
		String expected = "TEST\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testRead() {
		console.addIn("TEST");
		assertEquals("TEST", view.read());
	}
}