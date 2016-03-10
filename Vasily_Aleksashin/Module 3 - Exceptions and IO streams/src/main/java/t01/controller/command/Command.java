package t01.controller.command;

import t01.exception.ExitException;

public interface Command {
	void execute(String input) throws ExitException;
}
