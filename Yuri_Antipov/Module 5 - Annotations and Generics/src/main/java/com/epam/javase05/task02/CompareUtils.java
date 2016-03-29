package com.epam.javase05.task02;

import java.util.Arrays;

public class CompareUtils {

    public static <T extends Comparable> T min(T[] arr) {
        if (arr == null || arr.length == 0){
            return null;
        }
        T min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min.compareTo(arr[i]) > 0) {
                min = arr[i];
            }
        }
        return min;
    }

    public static <T extends Comparable> T max(T[] arr) {
        if (arr == null || arr.length == 0){
            return null;
        }
        T max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max.compareTo(arr[i]) < 0) {
                max = arr[i];
            }
        }
        return max;
    }

    public static <T extends Comparable> T median(T[] arr) {
        if (arr == null || arr.length == 0){
            return null;
        }
        Arrays.sort(arr);
        T median;
        if (arr.length % 2 == 0) {
            median = arr[(arr.length / 2 + arr.length / 2 - 1) / 2];
        } else {
            median = arr[arr.length/2];
        }
        return median;
    }
}
