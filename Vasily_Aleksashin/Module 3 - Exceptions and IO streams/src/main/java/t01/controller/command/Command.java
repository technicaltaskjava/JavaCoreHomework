package t01.controller.command;

import t01.exception.ExitException;
import t01.exception.ModelException;

public interface Command {
    boolean canExecute(String input);

    void execute(String input) throws ExitException;
}
