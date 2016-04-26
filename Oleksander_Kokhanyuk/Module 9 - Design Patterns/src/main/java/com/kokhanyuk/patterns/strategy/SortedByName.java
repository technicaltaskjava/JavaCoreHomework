package com.kokhanyuk.patterns.strategy;

import java.util.Comparator;

/**
 * SortedByName
 * <p>
 * This class contains a comparison algorithm for model cars.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SortedByName implements Comparator<Car> {

    @Override
    public int compare(Car obj1, Car obj2) {
        String str1 = obj1.getModel();
        String str2 = obj2.getModel();
        return str1.compareTo(str2);
    }
}