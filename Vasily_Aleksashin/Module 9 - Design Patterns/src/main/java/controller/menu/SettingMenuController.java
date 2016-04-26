package controller.menu;

import controller.MainController;
import model.XmlParser;
import model.datastore.DataFactory;
import model.parser.Parser;
import model.parser.impl.DomParserImpl;
import model.parser.impl.SaxParserImpl;
import model.parser.impl.StaxParserImpl;

class SettingMenuController {

	void show(final MainController controller) {
		final String menu = getMenu();
		boolean exitMenu = false;
		while (!exitMenu) {
			controller.print(menu);
			final String input = controller.read();
			switch (input) {
				case "0":
					setParser(controller);
					break;
				case "1":
					setDataStore(controller);
					break;
				case "2":
					exitMenu = true;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 2", input));
			}
		}
	}

	private void setDataStore(final MainController controller) {
		final String output = "Select type of DataStore:\n" +
				"[0] - List DataStore\n" +
				"[1] - Set DataStore";
		controller.print(output);
		final DataFactory factory = controller.getFactory();
		final String input = controller.read();
		switch (input) {
			case "0":
				controller.setUserData(factory.getListData());
				break;
			case "1":
				controller.setUserData(factory.getSetData());
				break;
			default:
				controller.print(String.format("%nEntered type of DataStore '%s' incorrect, default is SET", input));
				controller.setUserData(factory.getSetData());
		}
	}

	private void setParser(final MainController controller) {
		XmlParser parser = controller.getParser();
		Parser parserMethod;
		final String output = "Select parser method:\n" +
				"[0] - DOM\n" +
				"[1] - SAX\n" +
				"[2] - StAX";
		controller.print(output);
		final String input = controller.read();
		switch (input) {
			case "0":
				parserMethod = new DomParserImpl();
				break;
			case "1":
				parserMethod = new SaxParserImpl();
				break;
			case "2":
				parserMethod = new StaxParserImpl();
				break;
			default:
				controller.print(String.format("%nEntered parse method '%s' incorrect, default is DOM parser", input));
				parserMethod = new DomParserImpl();
		}
		if (parser == null) {
			controller.setParser(new XmlParser(parserMethod));
		} else {
			parser.setParser(parserMethod);
		}
	}

	private String getMenu() {
		return MainMenuController.SEPARATOR + "\n" +
				"\tApplication configuring Menu\n" +
				MainMenuController.SEPARATOR + "\n" +
				"[0] Set XML parser\n" +
				"[1] Set DataStore\n" +
				"[2] Back to Main Menu\n" +
				MainMenuController.SEPARATOR;
	}
}
