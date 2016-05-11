package controller.menu;

import controller.MainController;
import exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuController {
	static final String SEPARATOR = "=========================================\n";
	private static final Logger logger = LoggerFactory.getLogger(MainMenuController.class);
	private UserMenuController userMenu;
	private CookieMenuController cookieMenu;

	public void show(final MainController controller) {
		try {
			userMenu = new UserMenuController(controller);
			cookieMenu = new CookieMenuController(controller);
		} catch (DaoException e) {
			logger.error(e.getMessage(), e);
		}
		boolean isExit = false;
		while (!isExit) {
			controller.print(getMenu());
			final String input = controller.read();
			switch (input) {
				case "0":
					userMenu.show(controller);
					break;
				case "1":
					cookieMenu.show(controller);
					break;
				case "2":
					MetadataMenuController metadataMenu = new MetadataMenuController();
					metadataMenu.show(controller, userMenu, cookieMenu);
					break;
				case "3":
					AdvancedMenuController advancedMenu = new AdvancedMenuController();
					advancedMenu.show(controller);
					break;
				case "4":
					isExit = true;
					controller.print("Thank you for using my App");
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 4", input));
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
