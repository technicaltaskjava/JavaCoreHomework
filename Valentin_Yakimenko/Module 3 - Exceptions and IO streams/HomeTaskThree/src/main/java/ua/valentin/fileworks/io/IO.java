package ua.valentin.fileworks.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by valentin.yakimenko on 15.03.16.
 */
public class IO {
    private static final String ASK_FOR_COMMAND = "Input command:";
    private static final String ASK_FOR_TEXT = "Input text:";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void print(String outputString) {
        System.out.println(outputString);
    }

    public static String getCommand() throws IOException {
        return reader.readLine();
    }

    public static String askAndGetText() throws IOException {
        print(ASK_FOR_TEXT);
        return reader.readLine();
    }

    public static void askForCommand() {
        print(ASK_FOR_COMMAND);
    }
 }
