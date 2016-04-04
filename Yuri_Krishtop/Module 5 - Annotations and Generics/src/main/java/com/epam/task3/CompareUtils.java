package com.epam.task3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Yuriy Krishtop on 28.03.2016.
 */
public class CompareUtils<T> {

    public static <T extends Comparable> T min(T[] array) {
        T min = null;
        if (array == null || array.length == 0) {
            return null;
        } else {
            min = array[0];
            for (int i = 0; i < array.length; i++) {
                if (min.compareTo(array[i]) > 0) {
                    min = array[i];
                }
            }
        }
        return min;
    }

    public static <T extends Comparable> T max(T[] array) {
        T max = null;
        if (array == null || array.length == 0) {
            return null;
        } else {
            max = array[0];
            for (int i = 0; i < array.length; i++) {
                if (max.compareTo(array[i]) < 0) {
                    max = array[i];
                }
            }
        }
        return max;
    }

    public static <T extends Comparable> T median(T[] array) {
        T median = null;
        if (array == null || array.length == 0) {
            return null;
        } else {
            T[] arrayCopy = Arrays.copyOf(array, array.length);
            Arrays.sort(arrayCopy);
            median = arrayCopy[arrayCopy.length / 2];
        }
        return median;
    }


    public static <T> T min(T[] array, Comparator<T> comparator) {
        T min = null;
        if (array == null || array.length == 0) {
            return null;
        } else {
            min = array[0];
            for (int i = 1; i < array.length; i++) {
                if (comparator.compare(min, array[i]) > 0) {
                    min = array[i];
                }
            }
        }
        return min;
    }

    public static <T> T max(T[] array, Comparator<T> comparator) {
        T max = null;
        if (array == null || array.length == 0) {
            return null;
        } else {
            max = array[0];
            for (int i = 1; i < array.length; i++) {
                if (comparator.compare(array[i], max) > 0) {
                    max = array[i];
                }
            }
        }
        return max;
    }

    public static <T> T median(T[] array, Comparator<T> comparator) {
        T median = null;
        if (array == null || array.length == 0) {
            return null;
        } else {
            T[] arrayCopy = Arrays.copyOf(array, array.length);
            Arrays.sort(arrayCopy, comparator);
            median = arrayCopy[arrayCopy.length / 2];
        }
        return median;
    }


}
