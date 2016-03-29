package utils;

import java.util.Arrays;
import java.util.Comparator;

public class CompareUtils {

    public static <I extends Comparable> I getMin(I[] input){
        Arrays.sort(input);
        return input[0];
    }

    public static <I extends Comparable> I getMax(I[] input){
        Arrays.sort(input);
        return input[input.length-1];
    }

    public static <I extends Comparable> I getMedian(I[] input){
        Arrays.sort(input);
        return input[(input.length-1)/2];
    }

    public static <I> I getMin(I[] input, Comparator comp){
        Arrays.sort(input, comp);
        return input[0];
    }

    public static <I> I getMax(I[] input, Comparator comp){
        Arrays.sort(input, comp);
        return input[input.length-1];
    }
    public static <I> I getMedian(I[] input, Comparator comp){
        Arrays.sort(input, comp);
        return input[(input.length-1)/2];
    }
}
