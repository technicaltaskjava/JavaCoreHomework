package consoledialog;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Alexey Ushakov
 */
public class ConsoleDialog {

    public static final int EXIT_CODE = 0;
    private static final String[] ANSWER_YES = new String[]{ "y", "yes" };
    private static final String[] ANSWER_NO = new String[]{ "n", "no", "not" };
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
        output.println("[ " + EXIT_CODE + " ] Exit");

    }

    public static String getUserAnswerString(String message)
            throws IOException {
        output.print(message + " > ");
        if (input.hasNext()) {
            return input.next();
        }
        throw new CanReadAnswer();
    }

    public static String getUserAnswerString() throws IOException {
        return getUserAnswerString(System.getProperties().getProperty("user.name"));
    }

    public static int getUserAnswerInt(String message) throws IOException {
        output.print(message + " > ");
        if (input.hasNextInt()) {
            return input.nextInt();
        }
        throw new CanReadAnswer();
    }

    public static int getUserAnswerInt() throws IOException {
        return getUserAnswerInt(System.getProperties().getProperty("user.name"));
    }

    public static boolean askUser(String question) {
        output.print(question + "? [" + ANSWER_YES[0] + "/" + ANSWER_NO[0] + "] ");
        String userAnswer = "";
        if (input.hasNext()) {
            userAnswer = input.next();
        }
        for (String variants : ANSWER_YES) {
            if (userAnswer.toLowerCase().equals(variants)) {
                return true;
            }
        }
        return false;
    }

    public static void print(String s) {
        output.print(s);
    }

    public static void println(String s) {
        output.println(s);
    }

    public static void println() {
        output.println();
    }

    public static void close() {
        input.close();
        output.close();
    }

    public static void println(int value) {
        println(String.valueOf(value));
    }

    public static void main(String[] args) {
        System.out.println("Not implemented");
    }
}
