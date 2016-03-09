package t01.view.impl;

import t01.model.ShellPrompt;
import t01.view.View;

import java.util.Scanner;

public class ConsoleViewImpl implements View {
    private Scanner scanner = new Scanner(System.in);
    private final ShellPrompt prompt;

	public ConsoleViewImpl(final ShellPrompt prompt) {
		this.prompt = prompt;
	}

	@Override
    public void print(String output) {
        if (output != null) {
            System.out.println(output);
        }
        printPrompt();
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    private void printPrompt() {
        System.out.print(prompt.getPrompt());
    }
}
