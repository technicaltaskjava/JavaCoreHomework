package view.impl;

import model.t02.CrazyLogger;
import view.View;

import java.util.Scanner;

public class ConsoleViewImpl implements View {
	private static CrazyLogger logger = CrazyLogger.getLogger(ConsoleViewImpl.class);

	private Scanner scanner = null;

	@Override
	public void print(String output) {
		if (output != null) {
			System.out.println(output);
		}
	}

	@Override
	public String read() {
		logger.log(1, "Read from console");
		scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		return line;
	}

	@Override
	public boolean close() {
		logger.log(1, "Close Scanner");
		if (scanner != null) {
			scanner.close();
			return true;
		}
		return false;
	}
}
