package controller.menu;

import controller.MainController;

public class MainMenuController {

	static final String SEPARATOR = "=========================================\n";

	public void show(final MainController controller) {
		boolean isExit = false;
		while (!isExit) {
			controller.print(getMenu());
			final String input = controller.read();
			switch (input) {
				case "0":
					UserMenuController userMenu = new UserMenuController();
					userMenu.show(controller);
					break;
				case "1":
					break;
				case "2":
					break;
				case "3":
					AdvancedMenuController advancedMenu = new AdvancedMenuController();
					advancedMenu.show(controller);
					break;
				case "4":
					break;
				case "5":
					isExit = true;
					controller.print("Thank you for using my App");
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 5", input));
			}
		}
	}


	private String getMenu() {
		StringBuilder builder = new StringBuilder(SEPARATOR);
		builder.append("MAIN MENU").append("\n");
		builder.append(SEPARATOR);
		builder.append("[0] - User menu").append("\n");
		builder.append("[1] - Cookie menu").append("\n");
		builder.append("[2] - Metadata menu").append("\n");
		builder.append("[3] - Advanced menu").append("\n");
		builder.append("[4] - Setting menu").append("\n");
		builder.append("[5] - Exit").append("\n");
		builder.append(SEPARATOR);
		builder.append("Enter menu item:");
		return builder.toString();
	}
}
