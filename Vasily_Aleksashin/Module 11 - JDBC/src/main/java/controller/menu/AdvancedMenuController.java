package controller.menu;

import controller.MainController;
import model.service.TableService;

class AdvancedMenuController {
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
					isExit = true;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 2", input));
			}
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
		builder.append("[2] - Back to MAIN MENU").append("\n");
		builder.append(MainMenuController.SEPARATOR);
		builder.append("Enter menu item:");
		return builder.toString();
	}
}
