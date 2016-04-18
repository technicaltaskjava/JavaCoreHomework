package com.epam.first;

import java.util.*;
import java.util.logging.Logger;

public class FindSimpleNumbers {
    private static Logger log = Logger.getLogger(FindSimpleNumbers.class.getName());

    private FindSimpleNumbers() {
    }

    public static void mainMenu(Scanner userInput) {
        try {
            System.out.println("Enter the range start:");
            String userLine = userInput.nextLine();
            int startOfRange = Integer.parseInt(userLine);
            System.out.println("Enter the range end:");
            userLine = userInput.nextLine();
            int endOfRange = Integer.parseInt(userLine);
            System.out.println("Enter the number of threads:");
            userLine = userInput.nextLine();
            int numberOfThreads = Integer.parseInt(userLine);

            if (isInputCorrect(startOfRange, endOfRange, numberOfThreads)) {
                System.out.println("Work with joint collection:");
                findNumbers(startOfRange, endOfRange, numberOfThreads);
                System.out.println("Work with separate collections:");
                findNumbersWithOwnCollections(startOfRange, endOfRange, numberOfThreads);
            }
            else {
                System.out.println("Incorrect input. Try again.");
            }
        }
        catch (NumberFormatException | InterruptedException userException) {
            System.out.println("Incorrect input. You must input digit.");
            log.info(String.valueOf(userException));
        }
    }

    public static boolean isInputCorrect(Integer startOfRange, Integer endOfRange, Integer numberOfThreads) {
        if ((startOfRange >= endOfRange) || (startOfRange <= 0) || (endOfRange <= 0) || (numberOfThreads <= 0)) {
            return false;
        }
        return true;
    }

    public static void findNumbers(Integer startOfRange, Integer endOfRange,
                                   Integer numberOfThreads) throws InterruptedException {

        SortedSet<Integer> simpleNumbersSet = new TreeSet<>();
        SearchingThread[] threadArray = new SearchingThread[numberOfThreads];

        int currentRange = (endOfRange - startOfRange) / numberOfThreads;
        if ((endOfRange - startOfRange) % numberOfThreads != 0) {
            currentRange++;
        }

        long timeStart = System.currentTimeMillis();
        for (int threadCounter = 0; threadCounter < numberOfThreads; threadCounter++) {
            int currentStart = startOfRange + (currentRange * threadCounter);
            int currentEnd = startOfRange + (currentRange * (threadCounter + 1)) - 1;
            if (threadCounter == numberOfThreads - 1) {
                currentEnd = endOfRange;
            }
            threadArray[threadCounter] = new SearchingThread(currentStart, currentEnd, simpleNumbersSet);
            threadArray[threadCounter].start();
        }
        for (int threadCounter = 0; threadCounter < numberOfThreads; threadCounter++) {
            threadArray[threadCounter].join();
        }
        long timeEnd = System.currentTimeMillis();

        System.out.println("Number of threads: " + numberOfThreads);
        System.out.println("Searching time: " + (timeEnd - timeStart) + " ms");
        System.out.println("Count of numbers: " + simpleNumbersSet.size());
        System.out.println(simpleNumbersSet);
    }

    public static void findNumbersWithOwnCollections(Integer startOfRange, Integer endOfRange,
                                                     Integer numberOfThreads) throws InterruptedException {
        SortedSet<Integer> simpleNumbersSet = new TreeSet<>();
        SearchingThread[] threadArray = new SearchingThread[numberOfThreads];
        ArrayList<SortedSet<Integer>> ownSimpleNumbersSet = new ArrayList<>();
        for (int threadCounter = 0; threadCounter < numberOfThreads; threadCounter++) {
            ownSimpleNumbersSet.add(new TreeSet<>());
        }

        int currentRange = (endOfRange - startOfRange) / numberOfThreads;
        if ((endOfRange - startOfRange) % numberOfThreads != 0) {
            currentRange++;
        }

        long timeStart = System.currentTimeMillis();
        for (int threadCounter = 0; threadCounter < numberOfThreads; threadCounter++) {
            int currentStart = startOfRange + (currentRange * threadCounter);
            int currentEnd = startOfRange + (currentRange * (threadCounter + 1)) - 1;
            if (threadCounter == numberOfThreads - 1) {
                currentEnd = endOfRange;
            }
            threadArray[threadCounter] = new SearchingThread(currentStart, currentEnd,
                    ownSimpleNumbersSet.get(threadCounter));
            threadArray[threadCounter].start();
        }
        for (int threadCounter = 0; threadCounter < numberOfThreads; threadCounter++) {
            threadArray[threadCounter].join();
        }
        for (int threadCounter = 0; threadCounter < numberOfThreads; threadCounter++) {
            simpleNumbersSet.addAll(ownSimpleNumbersSet.get(threadCounter));
        }
        long timeEnd = System.currentTimeMillis();

        System.out.println("Number of threads: " + numberOfThreads);
        System.out.println("Searching time: " + (timeEnd - timeStart) + " ms");
        System.out.println("Count of numbers: " + simpleNumbersSet.size());
        System.out.println(simpleNumbersSet);
    }
}