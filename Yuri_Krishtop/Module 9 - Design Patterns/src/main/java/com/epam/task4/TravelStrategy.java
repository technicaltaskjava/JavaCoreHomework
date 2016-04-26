package com.epam.task4;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public interface TravelStrategy {
    public double calculateTime(int distance);
    public double calculatePrice(int distance);
}
