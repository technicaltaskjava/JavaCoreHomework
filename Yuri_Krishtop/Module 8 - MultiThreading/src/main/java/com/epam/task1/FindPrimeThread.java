package com.epam.task1;

import com.epam.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy Krishtop on 16.04.2016.
 */
public class FindPrimeThread extends Thread {
    private static ArrayList<Integer> primeListShared = new ArrayList<>();

    private int startRange;
    private int finishRange;
    private int threadNum;
    private long elapsedTime;
    boolean instantlyWrite;
    private ArrayList<Integer> primeList = new ArrayList<>();

    FindPrimeThread(int startRange, int finishRange, int threadNum, boolean instantlyWrite) {
        this(startRange, finishRange, threadNum);
        this.instantlyWrite = instantlyWrite;
    }

    FindPrimeThread(int startRange, int finishRange, int threadNum) {
        this.startRange = startRange;
        this.finishRange = finishRange;
        this.threadNum = threadNum;
    }

    public static synchronized void addPrime(int prime) {
        primeListShared.add(prime);
    }

    public static List<Integer> getPrimeListShared() {
        return primeListShared;
    }

    public List<Integer> getPrimeList() {
        return primeList;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        boolean isPrime = true;
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public static int countPrimeNumber(int start, int finish) {
        int countSimple = 0;
        int startNum = (start <= 1) ? 2 : start;
        for (int i = startNum; i <= finish; i++) {
            if (isPrime(i)) {
                countSimple++;
            }
        }
        return countSimple;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        int countPrime = 0;
        for (int i = startRange; i <= finishRange; i++) {
            if (isPrime(i)) {
                countPrime++;
                if (!instantlyWrite) {
                    primeList.add(i);
                } else {
                    addPrime(i);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        System.out.printf(Main.FORMAT_STRING_PIPE, "| Thread: " + threadNum + "  " +
                "founded prime numbers:  " + countPrime + " during " + (endTime - startTime) + " milliseconds", "|");
    }

    public static int[] calcLimitForThreads(int start, int finish, int countThreads) {
        int[] limits = new int[countThreads * 2];
        limits[0] = start;
        limits[limits.length - 1] = finish;
        int j = 1;
        for (int i = 1; i < countThreads; i++) {
            limits[j++] = start + (finish - start) * i / countThreads;
            limits[j++] = start + (finish - start) * i / countThreads + 1;
        }
        return limits;
    }

}
