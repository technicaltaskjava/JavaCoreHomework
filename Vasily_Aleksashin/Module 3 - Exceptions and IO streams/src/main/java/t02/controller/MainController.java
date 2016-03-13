package t02.controller;

import t01.exception.ExitException;
import t01.model.Environment;
import t01.model.file.FileOperations;
import t01.model.file.impl.FileOperationsImpl;
import t01.view.View;
import t01.view.impl.ConsoleViewImpl;
import t02.exception.PropertyException;
import t02.model.PropertiesService;
import t02.model.PropertiesServiceImpl;

import java.io.File;

public class MainController {
	public static final String SEPARATOR = "=======================================================";
	private View view = new ConsoleViewImpl();
	private FileOperations fileOperations = new FileOperationsImpl();
	private PropertiesService service = new PropertiesServiceImpl(fileOperations);

	public void run(final String[] args) throws ExitException {
		loadFromArgs(args);
		while (true) {
			view.print(SEPARATOR);
			view.print("Menu:");
			view.print(SEPARATOR);
			view.print("[1] Load from file " +
					"[2] Print all key/value " +
					"[3] Print value by key " +
					"[4] Add new key/value " +
					"[5] Update value by key " +
					"[6] Remove key/value " +
					"[7] Exit");
			view.print(SEPARATOR);
			view.print("Enter menu number:");
			String menuNumber = view.read();
			switch (menuNumber) {
				case "1":
					loadFile();
					break;
				case "2":
					view.print(service.toString());
					break;
				case "3":
					findValue();
					break;
				case "4":
					addKeyValue();
					break;
				case "5":
					updateValue();
					break;
				case "6":
					removeKey();
					break;
				case "7":
					view.close();
					throw new ExitException();
				default:
					view.print("Wrong menu number, expected from 1 to 6");
			}
		}
	}

	private void removeKey() {
		view.print("Enter key:");
		String inputKey = view.read();
		try {
			service.remove(inputKey);
			view.print("Operation successful");
		} catch (PropertyException e) {
			view.print(e.getMessage());
		}
	}

	private void updateValue() {
		view.print("Enter key:");
		String inputKey = view.read();
		view.print("Enter new value:");
		String inputValue = view.read();
		try {
			service.update(inputKey, inputValue);
			view.print("Operation successful");
		} catch (PropertyException e) {
			view.print(e.getMessage());
		}
	}

	private void addKeyValue() {
		view.print("Enter new key:");
		String inputKey = view.read();
		view.print("Enter new value:");
		String inputValue = view.read();
		try {
			service.add(inputKey, inputValue);
			view.print("Operation successful");
		} catch (PropertyException e) {
			view.print(e.getMessage());
		}
	}

	private void findValue() {
		view.print("Enter key:");
		String inputKey = view.read();
		try {
			String valueByKey = service.getValueByKey(inputKey);
			view.print(String.format("For key '%s' value is '%s'", inputKey, valueByKey));
		} catch (PropertyException e) {
			view.print(e.getMessage());
		}
	}

	private void loadFile() {
		view.print("Enter file name:");
		String inputFileName = view.read();
		view.print(String.format("Default separator for key/value is '%s'. " +
				"Do you want change this? (y/n)", service.getSeparator()));
		try {
			if (makeChoice()) {
				view.print("Enter new separator:");
				String separator = view.read();
				service.load(inputFileName, separator);
			} else {
				service.load(inputFileName);
			}
		} catch (PropertyException e) {
			view.print(e.getMessage());
		}
	}

	private boolean makeChoice() {
		while (true) {
			switch (view.read().toLowerCase()) {
				case "y":
					return true;
				case "n":
					return false;
				default:
					view.print("Expected 'y' or 'n'");
			}
		}
	}

	private void loadFromArgs(final String[] args) {
		if (args != null && args.length != 0) {
			view.print(SEPARATOR);
			view.print("Start loading properties file(s) from command arguments...");
			view.print(SEPARATOR);
			int count = 0;
			for (String arg : args) {
				try {
					File file = new File(arg);
					String filePath;
					if (file.isAbsolute()) {
						filePath = arg;
					} else {
						filePath = Environment.getWorkDir() + File.separator + arg;
					}
					service.load(filePath);
					count++;
				} catch (PropertyException e) {
					view.print("\t" + e.getMessage());
				}
			}
			view.print(SEPARATOR);
			if (count != 0) {
				view.print("Loading finished successful:");
			} else {
				view.print("Loading finished unsuccessfully:");
			}
			view.print(String.format("\ttotal file: %s,", args.length));
			view.print(String.format("\tloading file: %s,", count));
			view.print(SEPARATOR);
		}
	}
}
