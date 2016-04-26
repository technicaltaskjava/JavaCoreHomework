package com.epam.task4;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class TravelStrategyByAirplane implements TravelStrategy {

    public static final double AVERAGE_AIRPLANE_SPEED = 680.0;
    public static final double BASE_PRICE_ONE_KM_BY_AIRPLANE = 0.088;
    public static final double REGISTRATION_TIME = 1.5;

    @Override
    public double calculateTime(int distance) {
        return distance / AVERAGE_AIRPLANE_SPEED + REGISTRATION_TIME;
    }

    @Override
    public double calculatePrice(int distance) {
        return distance * BASE_PRICE_ONE_KM_BY_AIRPLANE * getDistanceMultilayer(distance);
    }

    private double getDistanceMultilayer(int distance) {
        if (distance < 300) {
            return 1;
        } else if (distance < 500) {
            return 0.9;
        } else if (distance < 1000) {
            return 0.7;
        } else {
            return 0.5;
        }
    }
}
