package edu.task2;

import java.util.Arrays;

/**
 * Created by O.Gondar on 28.03.2016.
 */
public class CompareUtils {

    private CompareUtils() {
    }

    public static <T extends Comparable> Unit<T> minMax(T[] a, boolean isSearchForMin) {

        if (a == null || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (int i = 0; i < a.length; i++) {

            if (min.compareTo(a[i]) > 0) {
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0) {
                max = a[i];
            }

        }
        return isSearchForMin ? new Unit<>(min) : new Unit<>(max);

    }

    public static <T extends Comparable> Unit<T> median(T[] a) {

        T median;
        if (a == null || a.length == 0) {
            return null;
        }
        Arrays.sort(a);
        median = a[a.length / 2];
        return new Unit<>(median);

    }

    public static <T extends Comparable> Unit<T> sortWithComparator(T[] a, MyComparator comparator, String whatFind) {

        T min = null;
        T max = null;

        Arrays.sort(a, comparator);

        if (a[0].compareTo(a[a.length - 1]) < 0) {
            min = a[0];
            max = a[a.length - 1];
        } else if (a[0].compareTo(a[a.length - 1]) > 0) {
            max = a[0];
            min = a[a.length - 1];
        }

        switch (whatFind) {
            case "min":
                return new Unit<>(min);

            case "max":
                return new Unit<>(max);

            case "median":
                return new Unit<>(a[a.length / 2]);

            default:
                return null;

        }
    }
}
