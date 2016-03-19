package t01.controller.command.impl;

import org.junit.Before;
import org.junit.Test;
import t01.controller.MainController;
import t01.controller.command.Command;
import t01.exception.ExitException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HelpTest {
    private Command help;
    private MainController controller;

    @Before
    public void setUp() {
        controller = mock(MainController.class);
        help = new Help(controller);
    }

    @Test
    public void testCanExecute() {
        assertTrue(help.canExecute("help"));
    }

    @Test
    public void testCanExecuteNull() {
        assertFalse(help.canExecute(null));
    }

    @Test
    public void testCanExecuteWrong() {
        assertFalse(help.canExecute("wrong"));
    }

    @Test
    public void testExecute() throws ExitException {
        help.execute("help");
        verify(controller).print(anyString());
    }
}