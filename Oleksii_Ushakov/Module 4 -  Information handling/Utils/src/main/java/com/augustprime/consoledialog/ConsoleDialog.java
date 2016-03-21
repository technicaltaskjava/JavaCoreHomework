package com.augustprime.consoledialog;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Alexey Ushakov
 */
public class ConsoleDialog {

    public static final int EXIT_FROM_MENU = 0;
    private static PrintStream output = System.out;
    private static Scanner input = new Scanner(System.in);
    private static String[] menuItem = new String[0];

    private ConsoleDialog() {
    }

    public static void setOutput(PrintStream output) {
        ConsoleDialog.output = output;
    }

    public static void setInput(InputStream input) {
        ConsoleDialog.input = new Scanner(input);
    }

    public static void addMenuItems(String[] items) {
        ConsoleDialog.menuItem = items;
    }

    public static void printMenu() {
        for (int i = 0; i < menuItem.length; i++) {
            output.println("[ " + (i + 1) + " ] " + menuItem[i]);
        }
        output.println("[ " + EXIT_FROM_MENU + " ] Exit");

    }

    public static String getUserAnswerString(String message)
            throws IOException {
        output.print(message + " > ");
        if (input.hasNext()) {
            String answer = input.next();
            input.nextLine();
            return answer;
        }
        throw new CanReadAnswerException();
    }

    public static String getUserAnswerString() throws IOException {
        return getUserAnswerString(System.getProperties().getProperty("user.name"));
    }

    public static int getUserAnswerInt(String message) throws IOException {
        output.print(message + " > ");
        if (input.hasNextInt()) {
            int answer = input.nextInt();
            input.nextLine();
            return answer;
        }
        throw new CanReadAnswerException();
    }

    public static int getUserAnswerInt() throws IOException {
        return getUserAnswerInt(System.getProperties().getProperty("user.name"));
    }

    public static boolean askUser(String question) {
        output.print(question + "? [y/n] ");
        String userAnswer = "";
        if (input.hasNext()) {
            userAnswer = input.next();
        }
        input.nextLine();
        if (userAnswer.matches("[Yy](es)?")) {
            return true;
        }
        return false;
    }

    public static void print(String s) {
        output.print(s);
    }

    public static void println(String s) {
        output.println(s);
    }

    public static void println(int value) {
        println(String.valueOf(value));
    }

    public static void println() {
        output.println();
    }

    public static void close() {
        input.close();
        output.close();
    }

}
