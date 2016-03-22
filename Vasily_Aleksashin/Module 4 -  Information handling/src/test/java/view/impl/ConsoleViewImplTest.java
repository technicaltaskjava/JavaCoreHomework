package view.impl;

import org.junit.Before;
import org.junit.Test;
import view.View;

import static org.junit.Assert.*;

public class ConsoleViewImplTest {
	private ConsoleMock console = new ConsoleMock();
	private View view;

	@Before
	public void setup() {
		view = new ConsoleViewImpl();
	}

	@Test
	public void testPrint() {
		view.print("TEST");
		String expected = "TEST\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testRead() {
		console.addIn("TEST");
		assertEquals("TEST", view.read());
	}

	@Test
	public void testCloseTrue() {
		console.addIn("TEST");
		view.read();
		assertTrue(view.close());
	}

	@Test
	public void testCloseFalse() {
		assertFalse(view.close());
	}
}