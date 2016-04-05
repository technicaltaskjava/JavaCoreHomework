package controller;

import controller.menu.MainMenuController;
import exception.ExitException;
import view.View;
import view.impl.ConsoleViewImpl;

public class MainController {
	private final View view = new ConsoleViewImpl();


	void run() throws ExitException {
		MainMenuController menu = new MainMenuController();
		try {
			menu.show(this);
		} catch (ExitException e) {
			view.close();
			throw e;
		}
	}

	public void print(final String output) {
		view.print(output);
	}

	public String read() {
		return view.read();
	}
}
