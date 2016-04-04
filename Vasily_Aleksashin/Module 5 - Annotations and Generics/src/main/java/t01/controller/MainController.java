package t01.controller;

import t01.exception.ExitException;
import t01.view.View;
import t01.view.impl.ConsoleViewImpl;

public class MainController {
	static final String DEFAULT_TEST_DIR = "target/classes/test";

	private final View view = new ConsoleViewImpl();
	private final TestController testController = new TestController();

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

	TestController getTestController() {
		return testController;
	}
}
