package com.epam.task1;

import com.epam.Main;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Yuriy Krishtop on 17.04.2016.
 */
public class ControllerFindPrime {

    private static ArrayList<Integer> primeList = new ArrayList<>();
    private static final Logger log = Logger.getLogger(ControllerFindPrime.class);

    private ControllerFindPrime() {
    }

    public static void main(int[] inputData) {
        if (isInputDataValid(inputData)) {
            System.out.println(Main.LINE);
            System.out.printf(Main.FORMAT_STRING_PIPE, "| Finding primes with saving in own" +
                    " collection for each tread", "|");
            runPrimesFind(inputData, false);
            System.out.println("===========================================================================================");
            System.out.println(Main.LINE);
            System.out.printf(Main.FORMAT_STRING_PIPE, "| Finding primes with instantly saving in shared" +
                    " collection for all tread", "|");
            runPrimesFind(inputData, true);
        } else {
            System.out.printf(Main.FORMAT_LINE_STRING_PIPE_LINE, Main.LINE, "| Entered numbers is not valid. ", "|",
                    Main.LINE);
        }
    }

    public static void runPrimesFind(int[] inputData, boolean instantlyWrite) {
        int start = inputData[0];
        int finish = inputData[1];
        int countThreads = inputData[2];
        int[] limits = FindPrimeThread.calcLimitForThreads(start, finish, countThreads);
        System.out.println(Main.LINE);
        FindPrimeThread[] threads = new FindPrimeThread[countThreads];
        int j = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new FindPrimeThread(limits[i + j], limits[i + j++ + 1], i, instantlyWrite);
            threads[i].start();
        }
        for (FindPrimeThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.error(e);
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Main.LINE);
        long timeInThreads = 0;
        for (FindPrimeThread thread : threads) {
            primeList.addAll(thread.getPrimeList());
            timeInThreads += thread.getElapsedTime();
        }
        System.out.printf(Main.FORMAT_STRING_PIPE, "| Total count of prime numbers: " + primeList.size(), "|");
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.printf(Main.FORMAT_STRING_PIPE, "| Total time in additional threads: " +
                timeInThreads + " milliseconds", "|");
        System.out.printf(Main.FORMAT_STRING_PIPE_LINE, "| Total time in master thread: " +
                elapsedTime + " milliseconds", "|", Main.LINE);

    }

    public static boolean isInputDataValid(int[] inputData) {
        int start = inputData[0];
        int finish = inputData[1];
        int countThreads = inputData[2];
        return (start > 0) && (finish > 0) && (countThreads > 0) && (finish > start);
    }
}