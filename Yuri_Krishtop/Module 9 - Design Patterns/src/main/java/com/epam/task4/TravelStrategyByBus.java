package com.epam.task4;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class TravelStrategyByBus implements TravelStrategy {

    public static final double AVERAGE_BUS_SPEED = 68.8;
    public static final double BASE_PRICE_ONE_KM_BY_BUS = 0.03;

    @Override
    public double calculateTime(int distance) {
        return distance / AVERAGE_BUS_SPEED;
    }

    @Override
    public double calculatePrice(int distance) {
        return distance * BASE_PRICE_ONE_KM_BY_BUS * getDistanceMultilayer(distance);
    }

    private double getDistanceMultilayer(int distance) {
        if (distance < 100) {
            return 1;
        } else if (distance < 300) {
            return 0.75;
        } else {
            return 0.65;
        }
    }
}
