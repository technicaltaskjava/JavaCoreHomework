package controller;

import controller.menu.MainMenu;
import exeption.ExitException;
import model.t02.CrazyLogger;
import view.View;
import view.impl.ConsoleViewImpl;

public class MainController {
	public static boolean useAppLoggerMsg = false;
	private static CrazyLogger logger = CrazyLogger.getLogger(MainController.class);
	private View view = new ConsoleViewImpl();
	private String input;

	public void run() throws ExitException {
		logger.log(1, "Star Main Controller");
		MainMenu menu = new MainMenu();
		logger.log(1, "Request for used logger");
		print("Do you want to use in testing the logger application messages? y/n");
		boolean flag = true;
		while (flag) {
			print("Enter answer:");
			input = read();
			switch (input.toLowerCase()) {
				case "y":
					logger.log(1, "Logger is used");
					useAppLoggerMsg = true;
					flag = false;
					break;
				case "n":
					logger.log(1, "Logger unused");
					flag = false;
					break;
				default:
					print(String.format("Enter '%s' but expected 'Y' or 'N'", input));
			}

		}
		try {
			logger.log(1, "Show main menu");
			menu.show(this);
		} catch (ExitException e) {
			logger.log(1, "App is closing");
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
