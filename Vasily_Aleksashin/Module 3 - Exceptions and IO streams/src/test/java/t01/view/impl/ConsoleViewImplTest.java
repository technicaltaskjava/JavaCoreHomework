package t01.view.impl;

import org.junit.Before;
import org.junit.Test;
import t01.model.directory.ShellPrompt;
import t01.model.directory.impl.ShellPromptImpl;
import t01.view.View;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsoleViewImplTest {
	private ConsoleMock console = new ConsoleMock();
	private ShellPrompt prompt;
	private View view;

	@Before
	public void setup() {
		prompt = mock(ShellPromptImpl.class);
		view = new ConsoleViewImpl(prompt);
	}

	@Test
	public void testPrint() {
		when(prompt.getPrompt()).thenReturn("> ");
		view.print("TEST");
		String expected = "TEST\r\n> ";
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