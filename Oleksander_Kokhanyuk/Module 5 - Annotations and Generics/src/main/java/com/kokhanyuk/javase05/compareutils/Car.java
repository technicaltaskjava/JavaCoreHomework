package com.kokhanyuk.javase05.compareutils;

/**
 * Car
 * <p/>
 * This class stores information about the car.
 * It is necessary to test the operation of other parts of the homework.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class Car implements Comparable {

    private String model;
    private int price;

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

    @Override
    public int compareTo(Object obj) {
        Car entry = (Car) obj;
        int result = model.compareTo(entry.model);
        if (result != 0) {
            return result;
        }
        result = price - entry.price;
        if (result != 0) {
            return result / Math.abs(result);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Model: " + this.model + ": Prise: " + this.price + " $.";
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
        return (int) (31 * price + ((null == model) ? 0 : model.hashCode()));
    }
}