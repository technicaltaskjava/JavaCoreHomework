package com.kokhanyuk.javase05.compareutils;

import java.util.Comparator;

/**
 * SortedByPrice
 *
 * This class contains a comparison algorithm for price cars.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class SortedByPrice implements Comparator<Car> {

    public int compare(Car obj1, Car obj2) {

        double price1 = obj1.getPrice();
        double price2 = obj2.getPrice();
        if(price1 > price2) {
            return 1;
        }
        else if(price1 < price2) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
