package com.kokhanyuk.collections.taxistation.cars;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CarVip extends Car {

    private int priceBar;

    public CarVip(String model, int price, int fuelconsumption) {
        super(model, price, fuelconsumption);
    }

    public void setPriceBar(int priceBar) {
        this.priceBar = priceBar;
    }

    public int getPriceBar() {
        return priceBar;
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

        CarVip car = (CarVip) obj;
        if (price != car.price) {
            return false;
        }
        if (fuelconsumption != car.fuelconsumption) {
            return false;
        }
        if (id != car.id) {
            return false;
        }
        if (priceBar != car.priceBar) {
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
        return (int) (31 * price + ((null == model) ? 0 : model.hashCode()) + 15 * fuelconsumption + 5 * id + 5 * priceBar);
    }
}
