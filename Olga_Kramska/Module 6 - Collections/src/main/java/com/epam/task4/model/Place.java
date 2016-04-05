package com.epam.task4.model;

/**
 * Created by Olga Kramska on 05-Apr-16.
 */
public class Place {
    private Car car;

    public Place(Car car) {
        this.car = car;
    }

    public Place(){
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
