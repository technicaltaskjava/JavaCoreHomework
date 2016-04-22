package controller.menu;

import controller.MainController;
import utility.Constant;

public class MainMenuController {
	public void show(final MainController controller) {
		boolean flag = true;
		while (flag) {
			controller.print(Constant.SEPARATOR);
			controller.print("\tMAIN MENU");
			controller.print(Constant.SEPARATOR);
			controller.print("[0] task 1 - Prime Checker Menu");
			controller.print("[1] task 2 - Statement Loader Menu");
			controller.print("[2] Exit");
			controller.print(Constant.SEPARATOR);
			final String input = controller.read();
			switch (input) {
				case "0":
					CheckerMenuController checkerMenu = new CheckerMenuController();
					checkerMenu.show(controller);
					break;
				case "1":
					TransactionMenuController statementMenu = new TransactionMenuController();
					statementMenu.show(controller);
					break;
				case "2":
					flag = false;
					controller.print("Thank you for using my App");
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 2", input));
			}
		}
	}
}
