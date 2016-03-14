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

public class WriteFileTest {
	private Command write;
	private MainController controller;
	private boolean condition;

	@Before
	public void setUp() {
		controller = mock(MainController.class);
		write = new WriteFile(controller);
	}

	@Test
	public void testCanExecuteWithNull() {
		assertFalse(write.canExecute(null));
	}

	@Test
	public void testCanExecuteWithShot() {
		assertFalse(write.canExecute("12"));
	}

	@Test
	public void testCanExecuteWithEndSpace() {
		assertFalse(write.canExecute("message > file "));
	}

	@Test
	public void testCanExecuteWithWriteCommand() {
		assertTrue(write.canExecute("message > file"));
	}

	@Test
	public void testExecuteWithParameter() throws ExitException {
		write.execute("message > file -a");
		verify(controller).print(anyString());
	}

	@Test
	public void testExecute() throws ExitException {
		write.execute("message > file");
		verify(controller).print(anyString());
	}

	@Test
	public void testExecuteWithoutFileName() throws ExitException {
		write.execute("message >  -a");
		verify(controller).print("\tEnter file name");
	}
}