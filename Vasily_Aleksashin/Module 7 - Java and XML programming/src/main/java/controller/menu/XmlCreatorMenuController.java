package controller.menu;

import controller.MainController;
import exception.FileIOException;
import exception.ParameterIsNullException;
import exception.XMLParseException;
import model.task2.WritePomXmlFile;
import model.task2.entity.DependElement;
import model.task2.entity.Pom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Constant;
import utility.LoadDepend;
import utility.TxtFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

class XmlCreatorMenuController {
	private static final Logger logger = LoggerFactory.getLogger(XmlCreatorMenuController.class);

	private final Pom pom = new Pom();

	public void show(final MainController controller) {
		boolean flag = initProject(controller, true);
		while (flag) {
			controller.print(Constant.SEPARATOR);
			controller.print("\tXML WRITER MENU");
			controller.print(Constant.SEPARATOR);
			controller.print("[0] Load list of dependencies and plugins from file");
			controller.print("[1] Print default list");
			controller.print("[2] Add dependency to XMLWriter");
			controller.print("[3] Add plugin to XMLWriter");
			controller.print("[4] Create custom pom.xml");
			controller.print("[5] Print custom pom.xml");
			controller.print("[6] Back to MAIN MENU");
			final String input = controller.read();
			switch (input) {
				case "0":
					loadDepend(controller);
					break;
				case "1":
					printDependList(controller);
					break;
				case "2":
					addDependency(controller);
					break;
				case "3":
					addPlugin(controller);
					break;
				case "4":
					createPomXml(controller);
					break;
				case "5":
					printPomXml(controller);
					break;
				case "6":
					flag = false;
					break;
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 6", input));
			}
		}
	}

	private void printDependList(MainController controller) {
		controller.print("List");
		controller.print("Dependencies:");
		Set<DependElement> elements = pom.getDependElements();
		printDepend(controller, elements);
		controller.print("Plugins:");
		elements = pom.getPluginElements();
		printDepend(controller, elements);
	}

	private void printDepend(MainController controller, Set<DependElement> elements) {
		if (!elements.isEmpty()) {
			for (DependElement element : elements) {
				controller.print("<dependency>");
				controller.print("\t<groupID>" + element.getGroupId() + "</groupID>");
				controller.print("\t<artifactId>" + element.getArtifactId() + "</artifactId>");
				controller.print("\t<version>" + element.getVersion() + "</version>");
				controller.print("</dependency>");
			}
		} else {
			controller.print("Empty list");
		}
	}

	private void loadDepend(MainController controller) {
		try {
			Set<DependElement> elements = getDependElements(controller, Constant.DEPEND_DATA_FILE);
			if (!elements.isEmpty()) {
				for (DependElement element : elements) {
					pom.addDependElement(element);
				}
			} else {
				controller.print("Can not load dependencies");
			}
			elements = getDependElements(controller, Constant.PLUGIN_DATA_FILE);
			if (!elements.isEmpty()) {
				for (DependElement element : elements) {
					pom.addPluginElement(element);
				}
			} else {
				controller.print("Can not load plugins");
			}
		} catch (FileIOException | ParameterIsNullException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private Set<DependElement> getDependElements(MainController controller, String dependDataFile) throws FileIOException {
		controller.print("Enter file path or empty for use default file");
		final String input = controller.read();
		final String fileName = "".equals(input) ? dependDataFile : input;
		final String dependData = TxtFileReader.read(fileName);
		return LoadDepend.loadDependency(dependData);
	}

	private void printPomXml(final MainController controller) {
		File file = new File(Constant.PATHNAME);
		if (!file.canRead()) {
			controller.print(String.format("Can not read file '%s'", file.getAbsolutePath()));
		}
		try (Scanner scanner = new Scanner(file)) {
			StringBuilder builder = new StringBuilder();
			while (scanner.hasNextLine()) {
				builder.append(scanner.nextLine()).append("\n");
			}
			controller.print(builder.toString());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void createPomXml(final MainController controller) {
		WritePomXmlFile writePomXmlFile = new WritePomXmlFile(pom);
		try {
			writePomXmlFile.write();
			File file = new File(Constant.PATHNAME);
			controller.print(String.format("Custom pom.xml file is created: '%s'", file.getAbsolutePath()));
		} catch (XMLParseException e) {
			logger.error(e.getMessage(), e);
		}

	}

	private void addPlugin(final MainController controller) {
		controller.print("Enter plugin info");
		try {
			final DependElement dependElement = getDependElement(controller);
			pom.addPluginElement(dependElement);
		} catch (ParameterIsNullException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private boolean initProject(final MainController controller, boolean flag) {
		controller.print(Constant.SEPARATOR);
		controller.print("\tXML WRITER");
		controller.print(Constant.SEPARATOR);
		controller.print("Enter project info");
		final DependElement rootDependElement;
		boolean result = flag;
		try {
			rootDependElement = getDependElement(controller);
			pom.setRootDependElement(rootDependElement);
		} catch (ParameterIsNullException e) {
			logger.error(e.getMessage(), e);
			result = false;
		}
		return result;
	}

	private void addDependency(final MainController controller) {
		controller.print("Enter dependency info");
		try {
			final DependElement dependElement = getDependElement(controller);
			pom.addDependElement(dependElement);
		} catch (ParameterIsNullException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private DependElement getDependElement(final MainController controller) throws ParameterIsNullException {
		controller.print("Enter GroupId:");
		final String groupId = controller.read();
		controller.print("Enter ArtifactId:");
		final String artifactId = controller.read();
		controller.print("Enter Version:");
		final String version = controller.read();
		return new DependElement(groupId, artifactId, version);
	}
}
