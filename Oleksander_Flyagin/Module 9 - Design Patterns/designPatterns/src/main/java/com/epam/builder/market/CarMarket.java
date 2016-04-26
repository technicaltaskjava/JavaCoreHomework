package com.epam.builder.market;

import com.epam.builder.car.Car;

import java.util.ArrayList;
import java.util.List;

public class CarMarket {

    private List<Car> market = new ArrayList();

    public void addCar(Car car) {
        market.add(car);
    }

    public void sell(String manufacturer, String model) {
        int count = 0;
        for (Car car : market) {
            if (car.getManufacturer().equals(manufacturer) || car.getModel().equals(model)) {
                System.out.println("SELL CAR \r\n" + car + "\r\n");
                market.remove(count);
                break;
            }
            else {
                System.out.println("Car not found");
            }

            count++;
        }
    }

    public void showAllCar() {
        for (Car car : market) {
            System.out.println(car);
        }
    }


}
