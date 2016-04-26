package com.epam.task4;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class TravelStrategyByShip implements TravelStrategy {
    public static final double AVERAGE_SHIP_SPEED = 25.1;
    public static final double BASE_PRICE_ONE_KM_BY_SHIP = 0.22;
    public static final double EMBARKING_TIME = 0.5;

    @Override
    public double calculateTime(int distance) {
        return distance / AVERAGE_SHIP_SPEED + EMBARKING_TIME;
    }

    @Override
    public double calculatePrice(int distance) {
        return distance * BASE_PRICE_ONE_KM_BY_SHIP * getDistanceMultilayer(distance);
    }

    private double getDistanceMultilayer(int distance) {
        if (distance < 500) {
            return 1;
        } else if (distance < 1000) {
            return 0.8;
        } else {
            return 0.5;
        }
    }
}
