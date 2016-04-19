package view;

import interval.Interval;
import org.apache.log4j.Logger;
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
    private static PrintStream out = System.out;//NOSONAR
    private static final Logger logger = Logger.getLogger("console");

    private PrimeNumberMenu() {
    }

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
                logger.error(String.format("Does not match the pattern [%d:%d]%n", Integer.MIN_VALUE, Integer.MAX_VALUE), e);
            }
        }

        return result;
    }

    public static void runSearchEngine(NumberSearchEngine engine) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        out.println(String.format("%n%s start", engine.getName()));

        engine.start();

        out.println(String.format("%s stop", engine.getName()));
        long stopTime = System.currentTimeMillis();

        out.println(String.format("%s find %d prime numbers", engine.getName(), engine.getSearchPrimesCount()));
        out.println(String.format("Passed %d millisecond%n", stopTime - startTime));
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            out.println("First you need to define the interval");
            Interval interval = new Interval(getInt("start"), getInt("end"));

            int threadCount = getInt("thread count");

            while (threadCount <= 0 || threadCount > interval.size()) {
                out.println("Thread count must be in [1:" + interval.size() + "]");
                threadCount = getInt("thread count");
            }

            runSearchEngine(new BufferedSearchEngine(interval, threadCount));
            runSearchEngine(new SingleSearchEngine(interval, threadCount));

        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
