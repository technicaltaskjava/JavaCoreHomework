package com.kokhanyuk.patterns.strategy;

import org.apache.log4j.Logger;

import java.util.TreeSet;

/**
 * ToRunStrategy
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class ToRunStrategy {
    private static Logger logger = Logger.getLogger(ToRunStrategy.class);

    private ToRunStrategy() {
    }

    public static void main(String[] args) {

        TreeSet<Car> carsName = new TreeSet(new SortedByName());
        TreeSet<Car> carsPrice = new TreeSet(new SortedByPrice());
        Car[] cars = new Car[6];
        cars[0] = new Car("Mers", 11500);
        cars[1] = new Car("Audi", 14000);
        cars[2] = new Car("BMW", 18000);
        cars[3] = new Car("Yoko", 12200);
        cars[4] = new Car("Ford", 1300);
        cars[5] = new Car("Opel", 11000);
        for (Car car : cars) {
            carsName.add(car);
            carsPrice.add(car);
        }
        logger.info(carsName);
        logger.info(carsPrice);
    }
}
