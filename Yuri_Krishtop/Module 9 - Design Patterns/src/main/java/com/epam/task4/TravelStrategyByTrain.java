package com.epam.task4;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class TravelStrategyByTrain implements TravelStrategy {
    public static final double AVERAGE_TRAIN_SPEED = 54.2;
    public static final double BASE_PRICE_ONE_KM_BY_TRAIN = 0.016;

    @Override
    public double calculateTime(int distance) {
        return distance / AVERAGE_TRAIN_SPEED;
    }

    @Override
    public double calculatePrice(int distance) {
        return distance * BASE_PRICE_ONE_KM_BY_TRAIN * getDistanceMultilayer(distance);
    }

    private double getDistanceMultilayer(int distance) {
        if (distance < 600 ) {
            return 1;
        } else if (distance < 800) {
            return 0.95;
        } else {
            return 0.75;
        }
    }
}
