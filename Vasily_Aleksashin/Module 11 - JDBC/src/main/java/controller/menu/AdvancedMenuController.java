package controller.menu;

import controller.MainController;
import exception.DaoException;
import model.script.SqlScriptLoader;
import model.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidKeyException;

class AdvancedMenuController {
	private static final Logger logger = LoggerFactory.getLogger(AdvancedMenuController.class);

	private TableService service;

	void show(MainController controller) {
		service = new TableService(controller.getFactory().getTableDao());
		boolean isExit = false;
		while (!isExit) {
			controller.print(getMenu());
			final String input = controller.read();
			switch (input) {
				case "0":
					createTable(controller);
					break;
				case "1":
					deleteTable(controller);
					break;
				case "2":
					loadScript(controller);
					break;
				case "3":
					isExit = true;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 3", input));
			}
		}
	}

	private void loadScript(MainController controller) {
		controller.print("Enter file path or empty for default file");
		final String input = controller.read();
		SqlScriptLoader loader = new SqlScriptLoader();
		String usersScript = null;
		if (!"".equals(input)) {
			usersScript = input;
		} else {
			try {
				usersScript = controller.getProperty("sql.fill.user");
			} catch (InvalidKeyException e) {
				logger.error(e.getMessage(), e);
			}
		}
		loader.getScript(usersScript);
		int result = -1;
		try {
			result = loader.load();
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
		}
		if (result != -1) {
			controller.print(String.format("Loaded %s rows", result));
		} else {
			controller.print(String.format("Cannot load file '%s'", usersScript));
		}
	}

	private void deleteTable(MainController controller) {
		controller.print("Enter table name:");
		final String input = controller.read();
		final boolean result = service.delete(input);
		if (result) {
			controller.print(String.format("Deleted table '%s'", input));
		} else {
			controller.print(String.format("Cannot delete table '%s'", input));
		}
	}

	private void createTable(MainController controller) {
		controller.print("Enter table name:");
		final String input = controller.read();
		final boolean result = service.create(input);
		if (result) {
			controller.print(String.format("Created table '%s'", input));
		} else {
			controller.print(String.format("Cannot create table '%s'", input));
		}
	}


	private String getMenu() {
		StringBuilder builder = new StringBuilder(MainMenuController.SEPARATOR);
		builder.append("ADVANCED MENU").append("\n");
		builder.append(MainMenuController.SEPARATOR);
		builder.append("[0] - Create table").append("\n");
		builder.append("[1] - Delete table").append("\n");
		builder.append("[2] - Load SQL script file in multi-threaded mode").append("\n");
		builder.append("[3] - Back to MAIN MENU").append("\n");
		builder.append(MainMenuController.SEPARATOR);
		builder.append("Enter menu item:");
		return builder.toString();
	}
}
