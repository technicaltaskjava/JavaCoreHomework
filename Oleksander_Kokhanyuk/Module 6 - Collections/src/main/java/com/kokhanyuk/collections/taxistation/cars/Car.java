package com.kokhanyuk.collections.taxistation.cars;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Car implements Comparable {
    protected String model;
    protected int price;
    protected int fuelconsumption;
    protected int id;

    public Car() {
        this.model = "Opel";
        this.price = 10000;
        this.fuelconsumption = 8;
    }

    public Car(String model, int price, int fuelconsumption) {
        this.model = model;
        this.price = price;
        this.fuelconsumption = fuelconsumption;

    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public int getFuelconsumption() {
        return fuelconsumption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object obj) {
        Car entry = (Car) obj;
        int result = fuelconsumption - entry.fuelconsumption;
        if (result != 0) {
            return result / Math.abs(result);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Model: " + this.model + ": Prise: " + this.price + "$." + " Fuel: " + fuelconsumption + " L/100km";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Car car = (Car) obj;
        if (price != car.price) {
            return false;
        }
        if (fuelconsumption != car.fuelconsumption) {
            return false;
        }
        if (id != car.id) {
            return false;
        }
        if (null == model) {
            return model == car.model;
        } else {
            if (!model.equals(car.model)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * price + ((null == model) ? 0 : model.hashCode()) + 15 * fuelconsumption + 5 * id);
    }
}
