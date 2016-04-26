package com.kokhanyuk.patterns.builder;

import com.kokhanyuk.patterns.strategy.Car;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public interface Builder {

    Car getCar();

    void buildModel(String model);

    void buildPrice(int price);

    void buildColor (String color);
}
