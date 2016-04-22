package com.epam.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Olga Kramska on 16-Apr-16.
 */
public class FindingLauncher {
    private static final Logger LOGGER = LoggerFactory.getLogger(FindingLauncher.class);

    private FindingLauncher() {
    }

    public static Set<Integer> findSimpleNumbers(int start, int end, int threadNumber) {
        validateInputData(start, end, threadNumber);

        Set<Integer> setForResults = new TreeSet<>();
        for (int i = 0; i < threadNumber; i++) {
            Set<Integer> resultsSet = new TreeSet<>();
            SimpleNumbersFinder finder = new SimpleNumbersFinder(start + (end / threadNumber) * i,
                    (end / threadNumber) * (i + 1) + end % threadNumber,
                    resultsSet);
            Thread thread = new Thread(finder);
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
                Thread.currentThread().interrupt();
            }

            setForResults.addAll(resultsSet);
        }
        return setForResults;
    }

    public static Set<Integer> findSimpleNumbers(int start, int end, int threadNumber, Set<Integer> setForResults) {
        validateInputData(start, end, threadNumber);

        for (int i = 0; i < threadNumber; i++) {
            SimpleNumbersFinder finder = new SimpleNumbersFinder(start + (end / threadNumber) * i,
                    (end / threadNumber) * (i + 1) + end % threadNumber,
                    setForResults);
            Thread thread = new Thread(finder);
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
        }
        return setForResults;
    }

    private static void validateInputData(int start, int end, int threadNumber) {
        if (start < 2 || end < 2 || end < start || threadNumber < 1) {
            throw new IllegalArgumentException("Wrong input data");
        }
    }
}
