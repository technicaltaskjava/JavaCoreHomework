package com.kokhanyuk.collections.taxistation.cars;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CarVipExtra extends CarVip {

    private int priceKaraoke;

    public CarVipExtra(String model, int price, int fuelconsumption) {
        super(model, price, fuelconsumption);
    }

    public void setPriceKaraoke(int priceKaraoke) {
        this.priceKaraoke = priceKaraoke;
    }

    public int getPriceKaraoke() {
        return priceKaraoke;
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

        CarVipExtra car = (CarVipExtra) obj;
        if (price != car.price) {
            return false;
        }
        if (fuelconsumption != car.fuelconsumption) {
            return false;
        }
        if (id != car.id) {
            return false;
        }
        if (priceKaraoke != car.priceKaraoke) {
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
        return (int) (31 * price + ((null == model) ? 0 : model.hashCode()) + 15 * fuelconsumption + 5 * id + 51 * priceKaraoke);
    }

}
