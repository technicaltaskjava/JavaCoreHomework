package com.epam.t02;

import java.util.Arrays;

public class CompareUtils<T> {

    private static <T> T[] arraySorter(T[] inputArray) {
        T[] sortedArray = (T[]) new Object[inputArray.length];
        System.arraycopy(inputArray, 0, sortedArray, 0, inputArray.length);
        Arrays.sort(sortedArray);
        return sortedArray;
    }

    private static <T> T[] arraySorterByComparator(T[] inputArray) {
        T[] sortedArray = (T[]) new Object[inputArray.length];
        System.arraycopy(inputArray, 0, sortedArray, 0, inputArray.length);
        GenericComparator genericComparator = new GenericComparator();
        Arrays.sort(sortedArray, genericComparator);
        return sortedArray;
    }

    public static <T> T minElement (T[] inputArray) {
        T[] sortedArray = arraySorter(inputArray);
        return sortedArray[0];
    }

    public static <T> T minElementByComparator (T[] inputArray) {
        T[] sortedArray = arraySorterByComparator(inputArray);
        return sortedArray[0];
    }

    public static <T> T maxElement (T[] inputArray) {
        T[] sortedArray = arraySorter(inputArray);
        return sortedArray[(sortedArray.length-1)];
    }

    public static <T> T maxElementByComparator (T[] inputArray) {
        T[] sortedArray = arraySorter(inputArray);
        return sortedArray[(sortedArray.length-1)];
    }

    public static <T> T medianElement (T[] inputArray) {
        T[] sortedArray = arraySorter(inputArray);
        return sortedArray[sortedArray.length/2];
    }

    public static <T> T medianElementByComparator (T[] inputArray) {
        T[] sortedArray = arraySorter(inputArray);
        return sortedArray[sortedArray.length/2];
    }
}
