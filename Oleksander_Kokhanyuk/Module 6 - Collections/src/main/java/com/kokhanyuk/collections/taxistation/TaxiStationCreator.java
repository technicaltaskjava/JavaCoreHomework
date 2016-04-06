package com.kokhanyuk.collections.taxistation;

import com.kokhanyuk.collections.taxistation.cars.Car;
import com.kokhanyuk.collections.taxistation.cars.CarVip;
import com.kokhanyuk.collections.taxistation.cars.CarVipExtra;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class TaxiStationCreator {

    private TaxiStationCreator() {
    }

    public static void creatorTaxi(MyTaxiStation taxi) {
        taxi.addCar(new CarVip("Audi", 12000, 10));
        taxi.addCar(new CarVipExtra("Mercedes", 15000, 15));
        taxi.addCar(new Car("Opel", 10000, 16));
        taxi.addCar(new Car("Opel Cadet", 10000, 8));
        taxi.addCar(new CarVipExtra("Mercedes S", 15000, 15));
        taxi.addCar(new Car("Opel", 10580, 12));
        taxi.addCar(new CarVip("Audi A", 12000, 10));
        taxi.addCar(new Car("Opel", 10000, 9));
        taxi.addCar(new Car("Opel", 10200, 8));
        taxi.addCar(new CarVipExtra("Mercedes", 15010, 15));
        taxi.addCar(new Car("Opel", 10000, 8));
        taxi.addCar(new Car("Opel", 10005, 6));
        taxi.addCar(new Car("Opel Cadet", 10000, 8));
        taxi.addCar(new CarVipExtra("Mercedes S", 15000, 15));
        taxi.addCar(new Car("Opel", 10580, 12));
        taxi.addCar(new CarVip("Audi", 12400, 10));
    }
}
