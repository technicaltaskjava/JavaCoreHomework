package view.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.View;

import java.util.Scanner;

public class ConsoleViewImpl implements View {
	private static final Logger logger = LoggerFactory.getLogger(ConsoleViewImpl.class);

	private Scanner scanner = null;

	@Override
	public void print(String output) {
		if (output != null) {
			logger.info(output);
		}
	}

	@Override
	public String read() {
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		logger.info(String.format("User input from the console: '%s'", line));
		return line;
	}

	@Override
	public void close() {
		if (scanner != null) {
			print("Scanner is closed");
			scanner.close();
		}
	}
}
