package t01.view.impl;

import org.junit.Before;
import org.junit.Test;
import t01.model.ShellPrompt;
import t01.model.impl.ShellPromptImpl;
import t01.view.View;

import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ConsoleViewImplTest {
	private ShellPrompt prompt;
	private Scanner scanner;
	private View view;

	@Before
	public void setup() {
		prompt = mock(ShellPromptImpl.class);
		view = new ConsoleViewImpl(prompt);
	}
	@Test
	public void testPrint() throws Exception {
		when(prompt.getPrompt()).thenReturn("> ");
		view.print("TEST");
		verify(prompt).getPrompt();
	}

	@Test
	public void testRead() throws Exception {
		String input = view.read();
		assertEquals("TEST", input);
	}

	@Test
	public void testClose() throws Exception {

	}
}