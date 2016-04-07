package controller.menu;

import controller.MainController;
import exception.ExitException;
import exception.ParameterValidateException;
import model.task3.StoreNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class StoreMenuController {
	private static final Logger logger = LoggerFactory.getLogger(StoreMenuController.class);

	private StoreNumber storeNumber;

	void show(final MainController mainController) throws ExitException {
		storeNumber = new StoreNumber();
		while (true) {
			StringBuilder builder = new StringBuilder(MainMenuController.SEPARATOR);
			builder.append("\n\tSTORE NUMBERS MENU")
					.append(MainMenuController.SEPARATOR)
					.append("\n[0] Add number\n" +
							"[1] Remove number\n" +
							"[2] Find number\n" +
							"[3] Show store numbers\n" +
							"[4] Exit")
					.append(MainMenuController.SEPARATOR)
					.append("\nEnter menu item:");
			mainController.print(builder.toString());
			String input = mainController.read();
			switch (input) {
				case "0":
					addNumber(mainController);
					break;
				case "1":
					removeNumber(mainController);
					break;
				case "2":
					findNumber(mainController);
					break;
				case "3":
					mainController.print(String.format("%nStore number%n%s", storeNumber.getStore().toString()));
					break;
				case "4":
					throw new ExitException();
				default:
					mainController.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 4", input));
			}
		}
	}

	private void findNumber(final MainController mainController) {
		final double number = getNumber(mainController);
		try {
			final Double result = storeNumber.find(number);
			if (result == null) {
				mainController.print(String.format("Can not find number '%.2f' in store", number));
			} else {
				mainController.print(String.format("For number '%.2f' result is '%.2f'", number, result));
			}
		} catch (ParameterValidateException e) {
			logger.error(MainMenuController.SOMETHING_HAPPENED, e);
		}
	}

	private void removeNumber(final MainController mainController) {
		final double number = getNumber(mainController);
		try {
			final boolean result = storeNumber.remove(number);
			if (!result) {
				mainController.print(String.format("Can not remove from store number '%.2f'", number));
			}
		} catch (ParameterValidateException e) {
			logger.error(MainMenuController.SOMETHING_HAPPENED, e);
		}
	}

	private void addNumber(final MainController mainController) {
		final double number = getNumber(mainController);
		try {
			final boolean result = storeNumber.add(number);
			if (!result) {
				mainController.print(String.format("Can not add to store number '%.2f'", number));
			}
		} catch (ParameterValidateException e) {
			logger.error(MainMenuController.SOMETHING_HAPPENED, e);
		}
	}

	private Double getNumber(final MainController mainController) {
		while (true) {
			mainController.print("Enter a number to add to the store:");
			final String input = mainController.read();
			try {
				return Double.parseDouble(input);
			} catch (NumberFormatException e) {
				mainController.print(String.format("Expected a number, but entered '%s'%nTry again", e.getMessage()));
			}
		}
	}
}
