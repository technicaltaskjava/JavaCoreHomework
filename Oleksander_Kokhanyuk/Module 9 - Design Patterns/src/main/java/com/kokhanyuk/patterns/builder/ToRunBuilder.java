package com.kokhanyuk.patterns.builder;

import com.kokhanyuk.patterns.strategy.Car;
import org.apache.log4j.Logger;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class ToRunBuilder {

    private static Logger logger = Logger.getLogger(ToRunBuilder.class);

    private ToRunBuilder() {

    }

    public static void main(String[] args) {
        Director dir = new Director();
        dir.setCarBuilder(new CarBuilder());
        dir.constructCar();
        Car car = dir.getCar();
        logger.info(car + " " + car.getColor());
    }
}
