package com.kokhanyuk.patterns.builder;

import com.kokhanyuk.patterns.strategy.Car;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CarBuilder implements Builder {
    private Car car = new Car();

    @Override
    public Car getCar() {
        return car;
    }

    @Override
    public void buildModel(String model) {
        car.setModel(model);
    }

    @Override
    public void buildPrice(int price) {
        car.setPrice(price);
    }

    @Override
    public void buildColor(String color) {
        car.setColor(color);
    }
}
