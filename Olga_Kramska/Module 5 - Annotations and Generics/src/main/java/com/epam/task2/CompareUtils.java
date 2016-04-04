package com.epam.task2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Olga Kramska on 25-Mar-16.
 */
public class CompareUtils {
    private CompareUtils() {
    }

    public static <T extends Comparable<? super T>> T min(T[] array) {
        if (!validateArray(array)) {
            return null;
        }

        T minimal = array[0];
        for (int i = 1; i < array.length; i++) {
            if (minimal.compareTo(array[i]) > 0) {
                minimal = array[i];
            }
        }
        return minimal;
    }

    public static <T> T min(T[] array, Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator must not be NULL");
        }

        if (!validateArray(array)) {
            return null;
        }

        T minimal = array[0];
        for (int i = 1; i < array.length; i++) {
            if (comparator.compare(minimal, array[i]) > 0) {
                minimal = array[i];
            }
        }
        return minimal;
    }

    public static <T extends Comparable<? super T>> T max(T[] array) {
        if (!validateArray(array)) {
            return null;
        }

        T maximal = array[0];
        for (int i = 1; i < array.length; i++) {
            if (maximal.compareTo(array[i]) < 0) {
                maximal = array[i];
            }
        }
        return maximal;
    }

    public static <T> T max(T[] array, Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator must not be NULL");
        }

        if (!validateArray(array)) {
            return null;
        }

        T maximal = array[0];
        for (int i = 1; i < array.length; i++) {
            if (comparator.compare(maximal, array[i]) < 0) {
                maximal = array[i];
            }
        }
        return maximal;
    }

    public static <T extends Comparable<? super T>> T median(T[] array) {
        return median(array, null);
    }

    public static <T> T median(T[] array, Comparator<? super T> comparator) {
        T[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray, comparator);
        return sortedArray[sortedArray.length / 2];
    }

    private static <T> boolean validateArray(T[] array) {
        return !(array == null || array.length == 0);
    }
}
