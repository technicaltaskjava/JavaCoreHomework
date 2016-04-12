package controller.menu;

import controller.MainController;
import exception.XMLParseException;
import model.task1.ParserService;
import model.task1.enums.ParserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Constant;
import utility.UrlReader;

import java.util.List;
import java.util.Set;

class XmlParserMenuController {
	private static final Logger logger = LoggerFactory.getLogger(XmlParserMenuController.class);

	private ParserService service = null;

	void show(final MainController controller) {
		boolean flag = true;
		while (flag) {
			controller.print(Constant.SEPARATOR);
			controller.print("\tXML PARSER MENU");
			controller.print(Constant.SEPARATOR);
			controller.print("[0] Load a list of all xml files");
			controller.print("[1] Parse XML file");
			controller.print("[2] Show a list of all the characters");
			controller.print("[3] Get stats on the character");
			controller.print("[4] Get stats for all characters");
			controller.print("[5] Get phrase for character");
			controller.print("[6] Back to MAIN MENU");
			final String input = controller.read();
			switch (input) {
				case "0":
					printXmlList(controller);
					break;
				case "1":
					loadXml(controller);
					break;
				case "2":
					printPersons(controller);
					break;
				case "3":
					getStat(controller);
					break;
				case "4":
					getStatForAll(controller);
					break;
				case "5":
					getPhrase(controller);
					break;
				case "6":
					flag = false;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 6", input));
			}
		}
	}

	private void printXmlList(MainController controller) {
		final List<String> xmlFiles = UrlReader.getXmlFiles();
		if (xmlFiles != null) {
			controller.print("List of XML files");
			for (String xmlFile : xmlFiles) {
				controller.print(xmlFile);
			}
		} else {
			controller.print("List of XML file not found");
		}
	}

	private void getPhrase(final MainController controller) {
		if (service != null) {
			controller.print(Constant.ENTER_CHARACTER_NAME);
			final String input = controller.read();
			final List<String> phrase = service.getPhrase(input);
			if (phrase != null) {
				controller.print(String.format("List of phrase for character '%s':", input));
				for (String element : phrase) {
					controller.print(element);
				}
			} else {
				controller.print(String.format(Constant.CHARACTER_NOT_FOUND, input));
			}
		} else {
			controller.print(Constant.LIST_OF_CHARACTERS_IS_EMPTY);
		}
	}

	private void getStatForAll(final MainController controller) {
		if (service != null) {
			final Set<String> persons = service.getPersons();
			if (persons != null) {
				for (String person : persons) {
					controller.print(Constant.SEPARATOR);
					printCharacterStat(controller, person);
				}
			} else {
				controller.print(Constant.LIST_OF_CHARACTERS_IS_EMPTY);
			}
		} else {
			controller.print(Constant.LIST_OF_CHARACTERS_IS_EMPTY);
		}
	}

	private void getStat(final MainController controller) {
		if (service != null) {
			controller.print(Constant.ENTER_CHARACTER_NAME);
			final String input = controller.read();
			printCharacterStat(controller, input);
		} else {
			controller.print(Constant.LIST_OF_CHARACTERS_IS_EMPTY);
		}
	}

	private void printCharacterStat(final MainController controller, final String input) {
		final int count = service.getCount(input);
		final int average = service.gatAverage(input);
		if (count >= 0 || average >= 0) {
			controller.print(String.format("Character:\t\t\t\t\t\t%s", input));
			controller.print(String.format("Count of phrase:\t\t\t\t%s", count));
			controller.print(String.format("The average number of words:\t%s", average));
		} else {
			controller.print(String.format(Constant.CHARACTER_NOT_FOUND, input));
		}
	}

	private void printPersons(final MainController controller) {
		if (service != null) {

			final Set<String> persons = service.getPersons();
			if (persons != null) {
				controller.print("\tList of characters:");
				for (String person : persons) {
					controller.print(person);
				}
			} else {
				controller.print(Constant.LIST_OF_CHARACTERS_IS_EMPTY);
			}
		} else {
			controller.print(Constant.LIST_OF_CHARACTERS_IS_EMPTY);
		}
	}

	private void loadXml(final MainController controller) {
		setParserType(controller);
		controller.print("Enter XML file name:");
		final String input = controller.read();
		try {
			service.loadXML(input);
		} catch (XMLParseException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void setParserType(final MainController controller) {
		boolean flag = true;
		while (flag) {
			controller.print("Select type of parser");
			controller.print("[0] DOM parser");
			controller.print("[1] SAX parser");
			controller.print("[2] StAX parser");
			final String input = controller.read();
			switch (input) {
				case "0":
					service = ParserService.getInstance(ParserType.DOM);
					flag = false;
					break;
				case "1":
					service = ParserService.getInstance(ParserType.SAX);
					flag = false;
					break;
				case "2":
					service = ParserService.getInstance(ParserType.STAX);
					flag = false;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 2", input));
			}
		}
	}
}
