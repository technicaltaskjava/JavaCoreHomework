package view;

import interval.Interval;
import interval.IntervalBoundariesException;
import search.NumberSearchEngine;
import search.engine.BufferedSearchEngine;
import search.engine.SingleSearchEngine;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Alexey Ushakov
 */
public class PrimeNumberMenu {
    private static PrintStream out = System.out;

    public static int getInt(String description) {
        boolean correct = false;
        int result = 0;

        while (!correct) {
            try {
                out.print("Input " + description + ": ");
                Scanner scanner = new Scanner(System.in);

                result = scanner.nextInt();
                correct = true;
            } catch (InputMismatchException e) {
                out.println(String.format("Does not match the pattern [%d:%d]%n", Integer.MIN_VALUE, Integer.MAX_VALUE));
            }
        }

        return result;
    }

    public static void runSearchEngine(NumberSearchEngine engine) {
        long startTime = System.currentTimeMillis();
        out.println(String.format("%n%s start", engine.getName()));

        engine.start();

        out.println(String.format("%s stop", engine.getName()));
        long stopTime = System.currentTimeMillis();

        out.println(String.format("%s find %d prime numbers", engine.getName(), engine.getSearchPrimesCount()));
        out.println(String.format("Passed %d millisecond%n", stopTime - startTime));
    }

    public static void main(String[] args) {
        try {
            out.println("First you need to define the interval");
            Interval interval = new Interval(getInt("start"), getInt("end"));
            int threadCount = getInt("thread count");

            runSearchEngine(new BufferedSearchEngine(interval, threadCount));
            runSearchEngine(new SingleSearchEngine(interval, threadCount));

        } catch (IntervalBoundariesException e) {
            out.println(e.getMessage());
        }
    }
}
