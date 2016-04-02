package task2;

import java.util.Arrays;
import java.util.Comparator;

public class CompareUtils {
    private CompareUtils() {
    }

    public static <T extends Comparable> T min(final T[] array) {
        T[] temp = Arrays.copyOf(array, array.length);
        Arrays.sort(temp);
        return temp[0];
    }

    public static <T> T min(final T[] array, final Comparator<T> comparator) {
        T[] temp = Arrays.copyOf(array, array.length);
        Arrays.sort(temp, comparator);
        return comparator.compare(temp[0], temp[temp.length - 1]) < 0 ? temp[0] : temp[temp.length - 1];
    }

    public static <T extends Comparable> T max(final T[] array) {
        T[] temp = Arrays.copyOf(array, array.length);
        Arrays.sort(temp);
        return temp[temp.length - 1];
    }

    public static <T> T max(final T[] array, final Comparator<T> comparator) {
        T[] temp = Arrays.copyOf(array, array.length);
        Arrays.sort(temp, comparator);
        return comparator.compare(temp[0], temp[temp.length - 1]) > 0 ? temp[0] : temp[temp.length - 1];
    }

    public static <T extends Comparable> T median(final T[] array) {
        T[] temp = Arrays.copyOf(array, array.length);
        Arrays.sort(temp);
        return temp[temp.length / 2];
    }

    public static <T> T median(final T[] array, final Comparator<T> comparator) {
        T[] temp = Arrays.copyOf(array, array.length);
        Arrays.sort(temp, comparator);
        return temp[temp.length / 2];
    }
}
