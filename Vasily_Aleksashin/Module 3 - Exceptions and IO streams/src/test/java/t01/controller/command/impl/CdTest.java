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

public class CdTest {
    private Command cd;
    private MainController controller;

    @Before
    public void setUp() {
        controller = mock(MainController.class);
        cd = new Cd(controller);
    }

    @Test
    public void testCanExecute() {
        assertTrue(cd.canExecute("cd "));
    }

    @Test
    public void testCanExecuteNull() {
        assertFalse(cd.canExecute(null));
    }

    @Test
    public void testCanExecuteWrong() {
        assertFalse(cd.canExecute("wrong"));
    }

    @Test
    public void testExecuteWrongPath() throws ExitException {
        cd.execute("cd ");
        verify(controller).print(anyString());
    }
}