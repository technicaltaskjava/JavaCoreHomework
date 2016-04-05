package com.kokhanyuk.collections.parking;

import com.kokhanyuk.collections.taxistation.cars.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class RunParking {

     Logger log = LoggerFactory.getLogger(RunParking.class);

    public void parking() {
        int size = 12;
        FastParking parking = new FastParking(size);
        for (int i = 0; i < 10; i++) {
            int place = (int) Math.round(Math.random() * size);
            if (parking.addCar(place, new Car()) && parking.addCar(0, new Car())) {
                log.info("No free places!");
            }
        }
        parking.remCar((int)7);
        log.info("\nCars on parking: " + parking.getCarsOnParking());
        log.info("\nFree place: " + parking.getFreePlace());
        log.info("\n" + parking);
    }
}
