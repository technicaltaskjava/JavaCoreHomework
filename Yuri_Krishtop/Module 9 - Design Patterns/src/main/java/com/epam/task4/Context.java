package com.epam.task4;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class Context {

    private TravelStrategy strategy;

    public Context(TravelStrategy strategy) {
        this.strategy = strategy;
    }

    public double calcPrice(int distance) {
        return strategy.calculatePrice(distance);
    }

    public double calcTime(int distance) {
        return strategy.calculateTime(distance);
    }
}
