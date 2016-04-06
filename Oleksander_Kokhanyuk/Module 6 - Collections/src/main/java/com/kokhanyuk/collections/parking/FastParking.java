package com.kokhanyuk.collections.parking;

import com.kokhanyuk.collections.taxistation.cars.Car;

/**
 * FastParking
 *
 *This class simulates the work car park
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FastParking {

    private int freePlace;
    private int carsOnParking = 0;
    private Car[] parking;

    public FastParking(int freePlace) {
        this.freePlace = freePlace;
        parking = new Car[freePlace];
    }

    public boolean addCar(int fromPlase, Car car) {
        if (fromPlase < parking.length) {
            for (int i = fromPlase; i < parking.length; i++) {
                if (parking[i] == null) {
                    parking[i] = car;
                    freePlace--;
                    carsOnParking++;
                    parking[i].setId(carsOnParking);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean remCar(int plase) {
        if (plase < parking.length && parking[plase] != null) {
            parking[plase] = null;
            freePlace++;
            carsOnParking--;
            return false;
        }
        return true;
    }

    public int getFreePlace() {
        return freePlace;
    }

    public int getCarsOnParking() {
        return carsOnParking;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parking.length; i++) {
            if (parking[i] != null) {
                sb.append(i + 1 + ": car> " + parking[i].getId() + " |");
            } else {
                sb.append(i + 1 + ": free |");
            }
        }
        return sb.toString();
    }
}
