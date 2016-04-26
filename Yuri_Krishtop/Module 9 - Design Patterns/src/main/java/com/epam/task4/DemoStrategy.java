package com.epam.task4;

/**
 * Created by Yuriy Krishtop on 25.04.2016.
 */
public class DemoStrategy {
    private static final String FORMAT_TIME = "%.1f";
    private static final String FORMAT_PRICE = "%.2f";
    private static final String TRAVEL_TIME = "Travel time: ";
    private static final String PRICE = "Price: ";
    private static final String HOURS = " hours.";

    private DemoStrategy() {
    }

    public static void main() {
        int distance = 1000;

        Context contextAirplane = new Context(new TravelStrategyByAirplane());
        System.out.println("Travel by airplane");
        System.out.println(TRAVEL_TIME + String.format(FORMAT_TIME, contextAirplane.calcTime(distance)) + HOURS);
        System.out.println(PRICE + String.format(FORMAT_PRICE, contextAirplane.calcPrice(distance)) + " $");

        Context contextBus = new Context(new TravelStrategyByBus());
        System.out.println("\nTravel by bys");
        System.out.println(TRAVEL_TIME + String.format(FORMAT_TIME, contextBus.calcTime(distance)) + HOURS);
        System.out.println(PRICE + String.format(FORMAT_PRICE, contextBus.calcPrice(distance)) + " $");

        Context contextTrain = new Context(new TravelStrategyByTrain());
        System.out.println("\nTravel by train");
        System.out.println(TRAVEL_TIME + String.format(FORMAT_TIME, contextTrain.calcTime(distance)) + HOURS);
        System.out.println(PRICE + String.format(FORMAT_PRICE, contextTrain.calcPrice(distance)) + " $");

        Context contextShip = new Context(new TravelStrategyByShip());
        System.out.println("\nTravel by ship");
        System.out.println(TRAVEL_TIME + String.format(FORMAT_TIME, contextShip.calcTime(distance)) + HOURS);
        System.out.println(PRICE + String.format(FORMAT_PRICE, contextShip.calcPrice(distance)) + " $");
    }
}
