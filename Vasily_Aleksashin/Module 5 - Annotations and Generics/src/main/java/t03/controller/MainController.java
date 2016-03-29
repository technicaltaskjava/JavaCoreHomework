package t03.controller;

import t01.exception.ExitException;
import t01.view.View;
import t01.view.impl.ConsoleViewImpl;

public class MainController {
	View view = new ConsoleViewImpl();

	public void run() throws ExitException {
		MenuController menuController = new MenuController();
		try {
			menuController.show(this);
		} catch (ExitException e) {
			view.close();
			print("Thank you for using my app");
			throw e;
		}
	}

	void print(final String output) {
		view.print(output);
	}

	String read() {
		return view.read();
	}
}
