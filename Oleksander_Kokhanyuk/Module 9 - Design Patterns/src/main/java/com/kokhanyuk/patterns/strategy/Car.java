package com.kokhanyuk.patterns.strategy;

/**
 * Car
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Car {

    private String model;
    private int price;

    private String color;

    public Car() {
        //car without parameters
    }

    public Car(String model, int price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.model + ": " + this.price + " $.";
    }
}