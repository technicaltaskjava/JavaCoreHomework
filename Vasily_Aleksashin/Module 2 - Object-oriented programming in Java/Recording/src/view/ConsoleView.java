package view;

import java.util.Scanner;

public class ConsoleView implements View {
	private Scanner scanner = null;

	@Override
	public void print(final String output) {
		System.out.println(output);
	}

	@Override
	public String read() {
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}
		return scanner.nextLine();
	}

	@Override
	public boolean closeResources() {
		if (scanner != null) {
			scanner.close();
			return true;
		}
		return false;
	}
}
