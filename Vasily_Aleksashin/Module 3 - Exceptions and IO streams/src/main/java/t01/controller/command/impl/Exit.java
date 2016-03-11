package t01.controller.command.impl;

import t01.controller.command.Command;
import t01.exception.ExitException;

public class Exit implements Command {
	private static final String cmdName = "exit";

    @Override
    public boolean canExecute(String input){
        return input != null && input.equals(cmdName);
    }

    @Override
	public void execute(final String input) throws ExitException {
        throw new ExitException();
	}
}
