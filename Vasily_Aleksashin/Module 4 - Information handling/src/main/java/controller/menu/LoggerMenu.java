package controller.menu;

import exeption.ExitException;
import model.t02.CrazyLogger;
import util.Constant;

public class LoggerMenu {
	static String log = null;
	private static CrazyLogger logger = CrazyLogger.getLogger(LoggerMenu.class);
	private static MainMenu menu;

	static void writeToLog(final String tempLog) {
		logger.log(1, "Write temp log from user manual test");
		if (log != null) {
			String input;
			boolean flag = true;
			menu.getController().print("Do you want to overwrite the log result? y/n");
			while (flag) {
				menu.getController().print("Enter answer");
				input = menu.getController().read();
				switch (input.toLowerCase()) {
					case "y":
						log = tempLog;
						flag = false;
						break;
					case "n":
						log += tempLog;
						flag = false;
						break;
					default:
						menu.getController().print(String.format("Enter '%s' but expected 'Y' or 'N'", input));
				}

			}
		} else {
			log = tempLog;
		}
	}

	void show(final MainMenu menu) throws ExitException {
		this.menu = menu;
		LoggerSearchMenu loggerSearchMenu = new LoggerSearchMenu();
		LoggerPrintMenu loggerPrintMenu = new LoggerPrintMenu();
		while (true) {
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print("\t\tCRAZY LOGGER MENU");
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print("[0] Add entry to logger\t\t\t" +
					"[1] Search entry by parameter\n" +
					"[2] Print entry to stream\t\t" +
					"[3] Clear logger\n" +
					"[4] Back to Main menu");
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print(Constant.ENTER_MENU_NUMBER);
			logger.log(1, "Enter menu number");
			String inputMenuNumber = menu.getController().read();
			switch (inputMenuNumber) {
				case "0":
					add(menu);
					break;
				case "1":
					logger.log(1, "Show Logger Search Menu");
					try {
						loggerSearchMenu.show(menu);
					} catch (ExitException e) {
						continue;
					}
					break;
				case "2":
					logger.log(1, "Show Logger Print Menu");
					try {
						loggerPrintMenu.show(menu);
					} catch (ExitException e) {
						continue;
					}
					break;
				case "3":
					logger.log(1, "Clear logger");
					logger.clear();
					break;
				case "4":
					logger.log(1, "Return to Main menu");
					throw new ExitException();
				default:
					menu.getController().print(String.format("Menu number '%s' not found, expected 0 - 2", inputMenuNumber));
			}
		}
	}

	private void add(final MainMenu menu) {
		logger.log(1, "Enter message for logger");
		menu.getController().print("Enter message for logger:");
		String input = menu.getController().read();
		logger.log(input);
	}
}
