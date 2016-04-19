package com.epam;

import com.epam.task1.ControllerFindPrime;
import com.epam.task2.ControllerBank;

import java.util.Scanner;

/**
 * Created by Yuriy Krishtop on 13.04.2016.
 */
public class Main {

    public static final String FORMAT_STRING_PIPE;
    public static final String FORMAT_STRING_PIPE_LINE;
    public static final String FORMAT_LINE_STRING_PIPE_LINE;
    public static final String WARNING_MESSAGE;
    public static final String LINE;

    static {
        FORMAT_STRING_PIPE = "%-85s %5s%n";
        FORMAT_STRING_PIPE_LINE = "%-70s %20s%n%s%n";
        FORMAT_LINE_STRING_PIPE_LINE = "%s%n%-70s %20s%n%s%n";
        WARNING_MESSAGE = "| Error, enter a positive integer.";
        LINE = "+-----------------------------------------------------------------------------------------+";
    }

    Main() {
    }

    public static void main(String[] args) {
        System.out.printf(FORMAT_LINE_STRING_PIPE_LINE, LINE, "|  Home Work 08: Multithreading", "|", LINE);
        System.out.printf(FORMAT_STRING_PIPE, "|  Please enter an integer number for start:", "|");
        System.out.printf(FORMAT_STRING_PIPE, "|    1 - Run prime numbers finder", "|");
        System.out.printf(FORMAT_STRING_PIPE, "|    2 - Run banking transactions with synchronized", "|");
        System.out.printf(FORMAT_STRING_PIPE, "|    3 - Run banking transactions with java.util.concurrent", "|");
        System.out.printf(FORMAT_STRING_PIPE_LINE, "|    4 - close", "|", LINE);
        int idOperation = getIntegerNumbersFromUser();
        switch (idOperation) {
            case 1:
                ControllerFindPrime.main(getInputsForNumberFinder());
                break;
            case 2:
                ControllerBank.main(true);
                break;
            case 3:
                ControllerBank.main(false);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.printf(FORMAT_LINE_STRING_PIPE_LINE, LINE, "| Command not found, please try again",
                        "|", LINE);
        }

    }

    public static int[] getInputsForNumberFinder() {
        System.out.printf(FORMAT_LINE_STRING_PIPE_LINE, LINE, "| Enter start number of range: ", "|", LINE);
        int startRange = getIntegerNumbersFromUser();
        System.out.printf(FORMAT_LINE_STRING_PIPE_LINE, LINE, "| Enter finish number of range: ", "|", LINE);
        int finishRange = getIntegerNumbersFromUser();
        System.out.printf(FORMAT_LINE_STRING_PIPE_LINE, LINE, "| Enter count of threads: ", "|", LINE);
        int countThreads = getIntegerNumbersFromUser();
        return new int[]{startRange, finishRange, countThreads};
    }

    public static int getIntegerNumbersFromUser() {
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            return in.nextInt();
        } else {
            System.out.printf(FORMAT_LINE_STRING_PIPE_LINE, LINE, WARNING_MESSAGE, "|", LINE);
            return -1;
        }
    }
}
