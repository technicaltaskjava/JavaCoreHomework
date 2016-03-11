package t01.view.impl;

import t01.model.ShellPrompt;
import t01.view.View;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ConsoleViewImpl implements View {
    private Scanner scanner = null;
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
        scanner = new Scanner(System.in, "windows-1251");

        String line = scanner.nextLine();
        try {
            return new String(line.getBytes("windows-1251"), Charset.forName("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public boolean close() {
        if (scanner != null) {
            scanner.close();
            return true;
        }
        return false;
    }

    private void printPrompt() {
        System.out.print(prompt.getPrompt());
    }
}
