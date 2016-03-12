package t02.controller;

import t01.exception.ModelException;
import t01.model.file.FileOperations;
import t01.model.file.impl.FileOperationsImpl;
import t01.view.View;
import t01.view.impl.ConsoleViewImpl;
import t02.exception.ParameterNullException;
import t02.model.Properties;
import t02.model.PropertiesImpl;
import t02.model.entity.Property;

import java.util.Scanner;

public class MainController {
	private View view = new ConsoleViewImpl();
	private Properties properties = new PropertiesImpl();
	private FileOperations fileOperations = new FileOperationsImpl();

	private String separator = "=";

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(final String separator) {
		if (separator != null) {
			this.separator = separator;
		}
	}

	public void load(final String fileName) throws ModelException {
		String loadFile = fileOperations.read(fileName);
		getProperties(loadFile);
		properties.toString();
	}

	private void getProperties(final String loadFile) {
		try (Scanner scanner = new Scanner(loadFile)){
			String line;
			while ((line = scanner.nextLine()) != null) {
				String key;
				String value;
				if (line.contains(separator)) {
					key = line.substring(0, line.indexOf(separator));
					value = line.substring(line.indexOf(separator) + separator.length(), line.length());
					try {
						properties.add(new Property(key, value));
					} catch (ParameterNullException e) {
						//TODO WTF
					}
				}
			}

		}
	}


}
