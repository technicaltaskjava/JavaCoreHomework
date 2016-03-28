package javase05.t02.compare;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Class with static utils to calculate min, max and medium functions
 * Created by Yury Vislobodsky on 25.03.2016.
 */
public class CompareUtils {
    public static <T> T min(T[] array) {
        return copyAndSortArray(array)[0];
    }

    public static <T> T min(T[] array, Comparator<T> comparator) {
        return copyAndSortArray(array, comparator)[0];
    }

    public static <T> T max(T[] array) {
        return copyAndSortArray(array)[array.length-1];
    }

    public static <T> T max(T[] array, Comparator<T> comparator) {
        return copyAndSortArray(array, comparator)[array.length-1];
    }

    public static <T> T median(T[] array) {
        return copyAndSortArray(array)[array.length / 2];
    }

    public static <T> T median(T[] array, Comparator<T> comparator) {
        return copyAndSortArray(array, comparator)[array.length / 2];
    }

    private static <T> T[] copyAndSortArray(T[] array) {
        T[] arrayCopy = array.clone();
        Arrays.sort(arrayCopy);
        return arrayCopy;
    }

    private static <T> T[] copyAndSortArray(T[] array, Comparator<T> comparator) {
        T[] arrayCopy = array.clone();
        Arrays.sort(arrayCopy, comparator);
        return arrayCopy;
    }
}
