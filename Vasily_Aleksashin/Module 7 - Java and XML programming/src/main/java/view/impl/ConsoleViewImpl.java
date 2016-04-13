package view.impl;

import view.View;

import java.util.Scanner;

public class ConsoleViewImpl implements View {
	private Scanner scanner = null;

	@Override
	public void print(String output) {
		if (output != null) {
			System.out.println(output);
		}
	}

	@Override
	public String read() {
		scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	@Override
	public void close() {
		if (scanner != null) {
			print("Scanner is closed");
			scanner.close();
		}
	}
}
