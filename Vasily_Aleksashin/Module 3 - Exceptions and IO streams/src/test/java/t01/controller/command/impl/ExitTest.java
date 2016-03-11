package t01.controller.command.impl;

import org.junit.Before;
import org.junit.Test;
import t01.controller.command.Command;
import t01.exception.ExitException;

import static org.junit.Assert.*;

public class ExitTest {

    private Command exit;

    @Before
    public void setUp() throws Exception {
        exit = new Exit();
    }

    @Test
    public void testCanExecute() {
        assertTrue(exit.canExecute("exit"));
    }

    @Test
    public void testCanExecuteWrongInput() {
        assertFalse(exit.canExecute(" "));
    }

    @Test
    public void testCanExecuteNull() {
        assertFalse(exit.canExecute(null));
    }

    @Test (expected = ExitException.class)
    public void testExecute() throws ExitException {
        exit.execute(null);
    }
}