package view;

import intagretion.UserInputStream;
import intagretion.UserOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;

public class ConsoleViewTest {
	private UserInputStream in;
	private UserOutputStream out;

	@Before
	public void setup() {
		in = new UserInputStream();
		out = new UserOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
	}

	@Test
	public void testPrint() throws Exception {
		View view = new ConsoleView();
		String expected = "TEST\r\n";
		view.print("TEST");
		assertEquals(expected, out.getLine());
	}

	@Test
	public void testRead() throws Exception {
		View view = new ConsoleView();
		in.add("TEST");
		String expected = "TEST";
		String actual = view.read();
		assertEquals(expected, actual);
	}

	@Test
	public void testCloseTrue() {
		View view = new ConsoleView();
		in.add("Test");
		view.read();
		assertTrue(view.closeResources());
	}

	@Test
	public void testCloseFalse() {
		View view = new ConsoleView();
		assertFalse(view.closeResources());
	}
}