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

public class DirTest {
    private Command dir;
    private MainController controller;

    @Before
    public void setUp() {
        controller = mock(MainController.class);
        dir = new Dir(controller);
    }

    @Test
    public void testCanExecute() {
        assertTrue(dir.canExecute("dir"));
    }

    @Test
    public void testCanExecuteNull() {
        assertFalse(dir.canExecute(null));
    }

    @Test
    public void testCanExecuteWrong() {
        assertFalse(dir.canExecute("wrong"));
    }

    @Test
    public void testExecuteWrongPath() throws ExitException {
        dir.execute("dir");
        verify(controller).print(anyString());
    }

}