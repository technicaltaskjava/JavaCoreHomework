package com.epam.task4.model;

import com.epam.task4.type.Brand;
import com.epam.task4.type.Color;

/**
 * Created by Olga Kramska on 02-Apr-16.
 */
public class Car {
    private Brand brand;
    private Color color;
    private String licencePlate;

    public Car(Brand brand, Color color, String licencePlate) {
        this.brand = brand;
        this.color = color;
        this.licencePlate = licencePlate;
    }

    public Brand getBrand() {
        return brand;
    }

    public Color getColor() {
        return color;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }

        Car car = (Car) o;
        return getLicencePlate().equals(car.getLicencePlate());

    }

    // There is no necessity to override hashCode in Car, but Sonar requires for it.
    @Override
    public int hashCode() {
        return getLicencePlate().hashCode();
    }
}
