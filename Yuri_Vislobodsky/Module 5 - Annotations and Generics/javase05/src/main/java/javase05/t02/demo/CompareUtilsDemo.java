package javase05.t02.demo;

import javase05.t02.compare.CompareUtils;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Demo class for Compare Utils
 * Created by Yury Vislobodsky on 26.03.2016.
 */
public class CompareUtilsDemo {
    public static <T> void outputResults(T[] array) {
        printResults(array, CompareUtils.min(array),
                CompareUtils.max(array),
                CompareUtils.median(array));
    }

    public static <T> void outputResults(T[] array, Comparator<T> comparator) {
        printResults(array, CompareUtils.min(array, comparator),
                CompareUtils.max(array, comparator),
                CompareUtils.median(array, comparator));
    }

    public static <T> void printResults(T[] array, T minValue, T maxValue, T medianValue) {
        System.out.println();
        System.out.println( "Array contains:" + Arrays.toString(array));
        System.out.println("min = " + minValue);
        System.out.println("max = " + maxValue);
        System.out.println("median = " + medianValue);
    }

    public static void main(String[] args) {
        Double[] arrayDouble = new Double[] { 10.2, 5.4, 2.6, 4.8, 7.4, 8.8 };
        Integer[] arrayInteger = new Integer[] { 7, 3, 8, 4, 6, 5, 2, 9 };
        Character[] arrayCharacter = new Character[] { 'h', 'e', 'l', 'l', 'o' };
        String[] arrayString = new String[] { "blackberry", "apple", "cucumber",
                "cabbage", "aubergine", "plum" };

        // default sorting
        outputResults(arrayDouble);
        outputResults(arrayInteger);
        outputResults(arrayCharacter);
        outputResults(arrayString);

        // sorting with comparator (for example, by length of the words)
        outputResults(arrayString,
                new Comparator<String>() {
                    public int compare(String e1, String e2) {
                        return e1.length() - e2.length();
                    }});
    }
}
