package controller.menu;

import exeption.ExitException;
import exeption.FileIOException;
import exeption.ParameterIsNullException;
import model.FileHandler;
import model.t02.CrazyLogger;
import util.Constant;

public class OperationMenu {
	static String sourceText = null;
	static String sourceHtml = null;
	static String targetText = null;
	static String targetHtml = null;
	static String menuSubName;
	private static CrazyLogger logger = CrazyLogger.getLogger(OperationMenu.class);

	void show(final MainMenu menu, final boolean selectorTextOrHtml) throws ExitException {
		if (selectorTextOrHtml) {
			menuSubName = "text";
		} else {
			menuSubName = "html";
		}
		InputMenu inputMenu = new InputMenu();
		ProcessorMenu processorMenu = new ProcessorMenu();
		while (true) {
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print(String.format("\t\tOPERATION MENU WITH %s", menuSubName.toUpperCase()));
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print(String.format("[0] Enter the source %1$s\t\t" +
					"[1] Select the operation with source %1$s\n" +
					"[2] Save result\t\t\t\t\t" +
					"[3] Back to Main menu", menuSubName));
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print(Constant.ENTER_MENU_NUMBER);
			logger.log(1, "Enter menu number");
			String inputMenuNumber = menu.getController().read();
			switch (inputMenuNumber) {
				case "0":
					try {
						logger.log(1, "Show Input menu");
						inputMenu.show(menu, selectorTextOrHtml);
					} catch (ExitException e) {
						continue;
					}
					break;
				case "1":
					logger.log(1, "Show Processor menu");
					try {
						if (selectorTextOrHtml) {
							processorMenu.showText(menu);
						} else {
							processorMenu.showHtml(menu);
						}
					} catch (ParameterIsNullException e) {
						logger.log(3, e.getMessage());
						menu.getController().print(e.getMessage());
						continue;
					} catch (ExitException e) {
						continue;
					}

					break;
				case "2":
					save(menu, selectorTextOrHtml);
					break;
				case "3":
					logger.log(1, "Return to Main menu");
					throw new ExitException();
				default:
					menu.getController().print(String.format("Menu number '%s' not found, expected 0 - 2", inputMenuNumber));
			}
		}
	}

	private void save(final MainMenu menu, final boolean selectorTextOrHtml) {
		logger.log(1, "Save source text to file");
		menu.getController().print("Enter file name:");
		String inputFileName = menu.getController().read();
		FileHandler fileHandler = new FileHandler();
		try {
			fileHandler.create(inputFileName);
			if (selectorTextOrHtml) {
				fileHandler.write(inputFileName, sourceText);
			} else {
				fileHandler.write(inputFileName, sourceHtml);
			}
		} catch (ParameterIsNullException | FileIOException e) {
			logger.log(3, e.getMessage());
			menu.getController().print(e.getMessage());
		}
	}
}
