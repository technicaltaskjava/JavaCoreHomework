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

public class NewFileTest {
	private Command newFile;
	private MainController controller;

	@Before
	public void setUp() {
		controller = mock(MainController.class);
		newFile = new NewFile(controller);
	}

	@Test
	public void testCanExecute() {
		assertTrue(newFile.canExecute("newFile "));
	}

	@Test
	public void testCanExecuteNull() {
		assertFalse(newFile.canExecute(null));
	}

	@Test
	public void testCanExecuteWrong() {
		assertFalse(newFile.canExecute("wrong"));
	}

	@Test
	public void testExecuteWrongPath() throws ExitException {
		newFile.execute("newFile ");
		String output = "\tCan not create file "+ System.getProperty("user.home") + "\\, the file already exists";
		verify(controller).print(output);
	}
}