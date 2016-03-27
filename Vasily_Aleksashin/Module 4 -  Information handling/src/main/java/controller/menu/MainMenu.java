package controller.menu;

import controller.MainController;
import exeption.ExitException;
import model.t02.CrazyLogger;
import util.Constant;

public class MainMenu {
	private static CrazyLogger logger = CrazyLogger.getLogger(MainMenu.class);

	private MainController controller;

	public void show(final MainController controller) throws ExitException {
		this.controller = controller;
		OperationMenu operationMenu = new OperationMenu();
		LoggerMenu loggerMenu = new LoggerMenu();
		while (true) {
			controller.print(Constant.SEPARATOR);
			controller.print("\t\tMAIN MENU");
			controller.print(Constant.SEPARATOR);
			controller.print("[0] Text operation\t\t\t\t" +
					"[1] Html operation\n" +
					"[2] CrazyLogger operation\t\t" +
					"[3] Exit");
			controller.print(Constant.SEPARATOR);
			controller.print(Constant.ENTER_MENU_NUMBER);
			logger.log(1, "Enter menu number");
			String inputMenuNumber = controller.read();
			switch (inputMenuNumber) {
				case "0":
					try {
						logger.log(1, "Show Operation menu");
						operationMenu.show(this, true);
					} catch (ExitException e) {
						continue;
					}
					break;
				case "1":
					try {
						logger.log(1, "Show Operation menu");
						operationMenu.show(this, false);
					} catch (ExitException e) {
						continue;
					}
					break;
				case "2":
					try {
						logger.log(1, "Show Logger menu");
						loggerMenu.show(this);
					} catch (ExitException e) {
						continue;
					}
					break;
				case "3":
					logger.log(1, "Return to Main controller");
					throw new ExitException("Thank you for using my app");
				default:
					controller.print(String.format("Menu number '%s' not found, expected 0 - 2", inputMenuNumber));
			}
		}
	}

	MainController getController() {
		return controller;
	}
}
