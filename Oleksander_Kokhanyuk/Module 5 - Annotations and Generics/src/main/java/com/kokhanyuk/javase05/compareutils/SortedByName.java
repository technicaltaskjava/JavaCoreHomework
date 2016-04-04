package com.kokhanyuk.javase05.compareutils;

import java.util.Comparator;

/**
 * SortedByName
 *
 * This class contains a comparison algorithm for model cars.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SortedByName implements Comparator<Car> {

    public int compare(Car obj1, Car obj2) {
        String str1 = obj1.getModel();
        String str2 = obj2.getModel();
        return str1.compareTo(str2);
    }
}