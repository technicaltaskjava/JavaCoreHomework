package controller.menu;

import controller.MainController;
import exeption.ExitException;
import exeption.ParameterIsNullException;
import model.t02.CrazyLogger;
import util.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LoggerSearchMenu {
	private static final String LEVEL = "[INFO ]";
	private static CrazyLogger logger = CrazyLogger.getLogger(LoggerSearchMenu.class);

	public void show(final MainMenu menu) throws ExitException {
		while (true) {
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print("\t\tSEARCH MENU");
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print("[0] Find by message\t\t" +
					"[1] Find by date\n" +
					"[2] Find by period\t\t" +
					"[3] Back to Logger menu");
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print(Constant.ENTER_MENU_NUMBER);
			logger.log(1, "Enter menu number");
			String inputMenuNumber = menu.getController().read();
			switch (inputMenuNumber) {
				case "0":
					searchMessage(menu);
					break;
				case "1":
					searchDate(menu);
					break;
				case "2":
					searchPeriod(menu);
					break;
				case "3":
					logger.log(1, "Return to Logger menu");
					throw new ExitException();
				default:
					menu.getController().print(String.format("Menu number '%s' not found, expected 0 - 2", inputMenuNumber));
			}
		}
	}

	private void searchDate(final MainMenu menu) {
		logger.log(1, "Search in logger by Date");
		menu.getController().print(String.format("Enter date in format '%s'", Constant.DATE_FORMAT));
		menu.getController().print("Enter date to search for:");
		String inputDate = menu.getController().read();
		SimpleDateFormat format = new SimpleDateFormat(Constant.DATE_FORMAT);
		try {
			String searchResult = logger.findLog(format.parse(inputDate));
			LoggerMenu.writeToLog(searchResult);
			printSearchResult(menu);
		} catch (ParameterIsNullException | ParseException e) {
			logger.log(3, e.getMessage());
			menu.getController().print(e.getMessage());
		}
	}

	private void searchPeriod(final MainMenu menu) {
		logger.log(1, "Search in logger by Period");
		menu.getController().print(String.format("Enter date in format '%s'", Constant.DATE_FORMAT));
		SimpleDateFormat format = new SimpleDateFormat(Constant.DATE_FORMAT);
		try {
			menu.getController().print("Enter start date to search for:");
			String inputDate = menu.getController().read();
			Date startDate = format.parse(inputDate);
			menu.getController().print("Enter end date to search for:");
			inputDate = menu.getController().read();
			Date endDate = format.parse(inputDate);
			String searchResult = logger.findLog(startDate, endDate);
			LoggerMenu.writeToLog(searchResult);
			printSearchResult(menu);
		} catch (ParameterIsNullException | ParseException e) {
			logger.log(3, e.getMessage());
			menu.getController().print(e.getMessage());
		}
	}

	private void searchMessage(final MainMenu menu) {
		logger.log(1, "Search in logger by Messages");
		menu.getController().print("To finish entering text, type '/exit'");
		menu.getController().print("Enter text to search for:");
		String[] parameter = null;
		if (!MainController.useAppLoggerMsg) {
			parameter = new String[1];
			parameter[0] = LEVEL;
		}
		String input;
		while (!(input = menu.getController().read()).toLowerCase().equals("/exit")) {
			if (parameter != null) {
				parameter = Arrays.copyOf(parameter, parameter.length + 1);
			} else {
				parameter = new String[1];
			}
			parameter[parameter.length - 1] = input;
		}
		try {
			String searchResult = logger.findLog(parameter);
			LoggerMenu.writeToLog(searchResult);
			printSearchResult(menu);
		} catch (ParameterIsNullException e) {
			logger.log(3, e.getMessage());
			menu.getController().print(e.getMessage());
		}
	}

	private void printSearchResult(final MainMenu menu) {
		if (LoggerMenu.log != null) {
			menu.getController().print(LoggerMenu.log);
		} else {
			menu.getController().print(Constant.NO_MESSAGE);
		}
	}
}
