package t01.controller;

import t01.controller.command.Command;
import t01.controller.command.impl.Dir;
import t01.controller.command.impl.Exit;
import t01.exception.ExitException;
import t01.model.ShellPrompt;
import t01.model.impl.ShellPromptImpl;
import t01.view.View;
import t01.view.impl.ConsoleViewImpl;

public class MainController {
	private ShellPrompt prompt;
	private View view;
	private Command[] commands;

	public void run() throws ExitException {
		init();
		while (true) {
			view.print("");
			String input = view.read();
			for (Command cmd : commands) {
				cmd.execute(input);
			}
		}
	}

	public void print(final String output) {
		view.print(output);
	}
	private void init() {
		prompt = ShellPromptImpl.getInstance();
		view = new ConsoleViewImpl(prompt);
		commands = new Command[] {new Exit(), new Dir(this)};
	}
}
