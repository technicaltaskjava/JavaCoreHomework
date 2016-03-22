package controller.menu;

import exeption.ExitException;
import exeption.FileIOException;
import exeption.ParameterIsNullException;
import model.FileHandler;
import model.t02.CrazyLogger;
import util.Constant;

import java.io.BufferedWriter;
import java.io.IOException;

public class LoggerPrintMenu {
	private static CrazyLogger logger = CrazyLogger.getLogger(LoggerPrintMenu.class);


	public void show(final MainMenu menu) throws ExitException {
		while (true) {
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print("\t\tSEARCH MENU");
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print("[0] Print to Console\t\t" +
					"[1] Save to file\n" +
					"[2] Back to Main menu");
			menu.getController().print(Constant.SEPARATOR);
			menu.getController().print(Constant.ENTER_MENU_NUMBER);
			logger.log(1, "Enter menu number");
			String inputMenuNumber = menu.getController().read();
			switch (inputMenuNumber) {
				case "0":
					printConsole(menu);
					break;
				case "1":
					try {
						printFile(menu);
					} catch (FileIOException | ParameterIsNullException e) {
						continue;
					}
					break;
				case "2":
					logger.log(1, "Return to Main menu");
					throw new ExitException();
				default:
					menu.getController().print(String.format("Menu number '%s' not found, expected 0 - 2", inputMenuNumber));
			}
		}
	}

	private void printFile(final MainMenu menu) throws FileIOException, ParameterIsNullException {
		logger.log(1, "Save log to file");
		FileHandler fileHandler = new FileHandler();
		menu.getController().print("Enter file name:");
		String inputFileName = menu.getController().read();
		String path = "src/main/resources/";
		String fileName = path + inputFileName;
		fileHandler.setFileName(fileName);
		try {
			fileHandler.create(fileName);
		} catch (ParameterIsNullException | FileIOException e) {
			menu.getController().print(e.getMessage());
			logger.log(3, e.getMessage());
			throw e;
		}
		BufferedWriter writer = null;
		try {
			writer = fileHandler.getWriter();
			if (LoggerMenu.log != null) {
				menu.getController().print("Do you want to save the results in the search log? y/n");
				String input;
				boolean flag = true;
				while (flag) {
					menu.getController().print("Enter answer:");
					input = menu.getController().read();
					switch (input.toLowerCase()) {
						case "y":
							fileHandler.write(fileName, LoggerMenu.log);
							flag = false;
							break;
						case "n":
							logger.print(writer);
							flag = false;
							break;
						default:
							menu.getController().print(String.format("Enter '%s' but expected 'Y' or 'N'", input));
					}

				}
			} else {
				logger.print(writer);
			}
		} catch (ParameterIsNullException | FileIOException | IOException e) {
			logger.log(3, e.getMessage());
			menu.getController().print(e.getMessage());
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					logger.log(3, e.getMessage());
				}
			}
		}
	}

	private void printConsole(final MainMenu menu) {
		logger.log(1, "Print log to Console");
		if (LoggerMenu.log != null) {
			menu.getController().print("Do you want to display the results in the log? y/n");
			String input;
			boolean flag = true;
			while (flag) {
				menu.getController().print("Enter answer:");
				input = menu.getController().read();
				switch (input.toLowerCase()) {
					case "y":
						menu.getController().print(LoggerMenu.log);
						flag = false;
						break;
					case "n":
						logger.print();
						flag = false;
						break;
					default:
						menu.getController().print(String.format("Enter '%s' but expected 'Y' or 'N'", input));
				}

			}
		} else {
			logger.print();
		}
	}
}
