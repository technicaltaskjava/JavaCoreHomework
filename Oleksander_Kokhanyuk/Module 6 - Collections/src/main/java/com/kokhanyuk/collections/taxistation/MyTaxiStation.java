package com.kokhanyuk.collections.taxistation;

import com.kokhanyuk.collections.taxistation.cars.Car;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyTaxiStation<T extends Car> {

    private ArrayList<T> taxiStation;

    public MyTaxiStation() {
        taxiStation = new ArrayList();
    }

    public void addCar(T car) {
        car.setId(taxiStation.size() + 1);
        taxiStation.add(car);
    }

    public T getCar(int nomber) {
        if (nomber >= 1) {
            return taxiStation.get(nomber - 1);
        }
        return null;
    }

    public int getTotalCar() {
        return taxiStation.size();
    }
    public void taxiSorting(){
        Collections.sort(taxiStation);
    }
}
