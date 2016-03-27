package controller.menu;

import exeption.ExitException;
import exeption.FileIOException;
import exeption.ParameterIsNullException;
import model.FileHandler;
import model.t02.CrazyLogger;
import util.Constant;

public class InputMenu {
	private static CrazyLogger logger = CrazyLogger.getLogger(InputMenu.class);

	private String tempText;

	void show(final MainMenu menu, final boolean selectorTextOrHtml) throws ExitException {
		getSourceText(selectorTextOrHtml);
		while (true) {
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print(String.format("\t\tSOURCE %s MENU", OperationMenu.menuSubName.toUpperCase()));
			menu.getController().print(Constant.SEPARATOR);
			String format = String.format("[0] Load %1$s from file\t\t" +
					"[1] Enter %1$s from console\n" +
					"[2] Show source %1$s\t\t" +
					"[3] Back to %1$s menu", OperationMenu.menuSubName);
			menu.getController().print(format);
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print(Constant.ENTER_MENU_NUMBER);
			logger.log(1, "Enter menu number");
			String inputMenuNumber = menu.getController().read();
			switch (inputMenuNumber) {
				case "0":
					try {
						loadFile(menu, selectorTextOrHtml);
					} catch (ParameterIsNullException | FileIOException e) {
						menu.getController().print(e.getMessage());
					}
					break;
				case "1":
					inputText(menu);
					break;
				case "2":
					showSource(menu);
					break;
				case "3":
					logger.log(1, "Return to Oparation menu");
					setSourceText(selectorTextOrHtml);
					throw new ExitException();
				default:
					menu.getController().print(String.format("Menu number '%s' not found, expected 0 - 2", inputMenuNumber));
			}
		}
	}

	private void showSource(final MainMenu menu) {
		logger.log(1, "Show source text");
		if (tempText == null || tempText.length() == 0) {
			menu.getController().print(Constant.NO_MESSAGE);
		} else {
			menu.getController().print(tempText);
		}
	}

	private void setSourceText(final boolean selectorTextOrHtml) {
		logger.log(1, "Set source text from temp result");
		if (selectorTextOrHtml) {
			OperationMenu.sourceText = tempText;
		} else {
			OperationMenu.sourceHtml = tempText;
		}
	}

	private void getSourceText(final boolean selectorTextOrHtml) {
		logger.log(1, "Set temp result from source text");
		if (selectorTextOrHtml) {
			tempText = OperationMenu.sourceText;
		} else {
			tempText = OperationMenu.sourceHtml;
		}
	}

	private void inputText(final MainMenu menu) {
		logger.log(1, "Set temp result from console");
		menu.getController().print(String.format("To finish entering %s, type '/exit'", OperationMenu.menuSubName));
		menu.getController().print("Enter text:");
		StringBuilder builder = new StringBuilder();
		String input;
		while (!(input = menu.getController().read()).toLowerCase().equals("/exit")) {
			builder.append(input).append("\n");
		}
		tempText = builder.toString();
	}

	private void loadFile(final MainMenu menu, final boolean selectorTextOrHtml) throws FileIOException, ParameterIsNullException {
		logger.log(1, "Load temp result from file");
		menu.getController().print(String.format("To load the default %s file, type '/default'", OperationMenu.menuSubName));
		menu.getController().print("Enter file name:");
		String inputFileName = menu.getController().read();
		FileHandler fileHandler = new FileHandler();
		if (inputFileName.toLowerCase().equals("/default")) {
			String fileName;
			try {
				if (selectorTextOrHtml) {
					fileName = getClass().getClassLoader().getResource(Constant.DEFAULT_TEXT_FILE).getFile();
				} else {
					fileName = getClass().getClassLoader().getResource(Constant.DEFAULT_HTML_FILE).getFile();
				}
				fileName = fileName.replaceAll("%20", " ");
			} catch (NullPointerException e) {
				logger.log(3, e.getMessage());
				throw new FileIOException(e.getMessage());
			}
			tempText = fileHandler.read(fileName);
		} else {
			tempText = fileHandler.read(inputFileName);
		}
	}
}
