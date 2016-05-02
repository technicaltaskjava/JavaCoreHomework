package com.epam.builder;

import com.epam.builder.car.CarBuilder;
import com.epam.builder.market.CarMarket;

public class CarsToMarket {
    private static final String MERCEDES = "MERCEDES";
    private static final String BMW = "BMW";


    private   CarMarket carMarket = new CarMarket();
    void carsGoToMarkets() {

        carMarket.addCar(new CarBuilder().setManufacturer(MERCEDES).setModel("E220")
                .setIssue(2012).setPrice(60512).build());
        carMarket.addCar(new CarBuilder().setManufacturer(BMW).setModel("E34")
                .setIssue(1996).setState("previously used").setPrice(10710).build());
        carMarket.addCar(new CarBuilder().setManufacturer(MERCEDES).setModel("Sprinter")
                .setIssue(2003).setState("previously used").setPrice(35800).setTypeCar("minibus").build());
        carMarket.showAllCar();
        carMarket.sell(MERCEDES, "E220");
        carMarket.showAllCar();
    }
}
