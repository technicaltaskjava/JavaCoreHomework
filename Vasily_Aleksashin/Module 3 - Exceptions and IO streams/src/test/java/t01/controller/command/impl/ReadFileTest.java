package t01.controller.command.impl;

import org.junit.Before;
import org.junit.Test;
import t01.controller.MainController;
import t01.controller.command.Command;
import t01.exception.ExitException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReadFileTest {
	private Command readFile;
	private MainController controller;

	@Before
	public void setUp() {
		controller = mock(MainController.class);
		readFile = new ReadFile(controller);
	}

	@Test
	public void testCanExecute() {
		assertTrue(readFile.canExecute("read "));
	}

	@Test
	public void testCanExecuteNull() {
		assertFalse(readFile.canExecute(null));
	}

	@Test
	public void testCanExecuteWrong() {
		assertFalse(readFile.canExecute("wrong"));
	}

	@Test
	public void testExecuteWrongPath() throws ExitException {
		readFile.execute("read ");
		String output = "\tEnter file name";
		verify(controller).print(output);
	}

	@Test
	public void testExecute() throws ExitException {
		readFile.execute("read file");
		verify(controller).print(anyString());
	}
}