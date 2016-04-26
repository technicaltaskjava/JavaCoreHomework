package com.epam.builder;

import com.epam.builder.car.CarBuilder;
import com.epam.builder.market.CarMarket;

public class Sell {
    private Sell(){}

    public static void main(String[] args) {
        CarMarket carMarket = new CarMarket();

        carMarket.addCar(new CarBuilder().setManufacturer("Mercedes").setModel("E220")
                .setIssue(2012).setPrice(60512).build());
        carMarket.addCar(new CarBuilder().setManufacturer("BMW").setModel("E34")
                .setIssue(1996).setState("previously used").setPrice(10710).build());
        carMarket.addCar(new CarBuilder().setManufacturer("Mercedes").setModel("Sprinter")
                .setIssue(2003).setState("previously used").setPrice(35800).setTypeCar("minibus").build());
         carMarket.showAllCar();
         carMarket.sell("Mercedes", "E220");
         carMarket.showAllCar();



    }
}
