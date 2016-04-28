package com.kokhanyuk.patterns.builder;

import com.kokhanyuk.patterns.strategy.Car;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Director {
    private CarBuilder carBuilder;

    public void setCarBuilder(CarBuilder carBuilder) {
        this.carBuilder = carBuilder;
    }

    public Car getCar() {
        return carBuilder.getCar();
    }

    public void constructCar() {

        carBuilder.buildModel("Chery");
        carBuilder.buildPrice(9582);
        carBuilder.buildColor("Red");
    }
}
