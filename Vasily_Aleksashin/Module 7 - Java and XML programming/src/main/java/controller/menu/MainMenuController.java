package controller.menu;

import controller.MainController;
import exception.ExitException;
import utility.Constant;

public class MainMenuController {

	public void show(final MainController controller) throws ExitException {
		//noinspection InfiniteLoopStatement
		while (true) {
			controller.print(Constant.SEPARATOR);
			controller.print("\tMAIN MENU");
			controller.print(Constant.SEPARATOR);
			controller.print("[0] XML parser");
			controller.print("[1] XML document creation");
			controller.print("[2] Exit");
			controller.print(Constant.SEPARATOR);
			final String input = controller.read();
			switch (input) {
				case "0":
					XmlParserMenuController xmlParserMenu = new XmlParserMenuController();
					xmlParserMenu.show(controller);
					break;
				case "1":
					XmlCreatorMenuController xmlCreatorMenu = new XmlCreatorMenuController();
					xmlCreatorMenu.show(controller);
					break;
				case "2":
					throw new ExitException();
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 2", input));
			}
		}
	}
}
