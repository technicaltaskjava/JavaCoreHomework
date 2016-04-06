package com.kokhanyuk.collections.taxistation;

import com.kokhanyuk.collections.mylist.MyList;
import com.kokhanyuk.collections.taxistation.cars.Car;
import com.kokhanyuk.collections.taxistation.cars.CarVip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class RunTaxiStation {

    Logger log = LoggerFactory.getLogger(RunTaxiStation.class);

    public void taxi() {
        int priceTaxiStation = 0;
        MyTaxiStation taxi = new MyTaxiStation();
        TaxiStationCreator.creatorTaxi(taxi);
        MyList<Car> list = new MyList();
        for (int i = 1; i <= taxi.getTotalCar(); i++) {
            priceTaxiStation += taxi.getCar(i).getPrice();
            if (taxi.getCar(i).getFuelconsumption() >= 10 & taxi.getCar(i).getFuelconsumption() <= 12) {
                list.add(taxi.getCar(i));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof CarVip) {
                log.info("Found Vip Car: " + list.get(i));
            }
        }
        log.info("Total price Taxi Station: " + priceTaxiStation + "$");
        taxi.taxiSorting();
        log.info(String.valueOf("Minimum fuel consumption: " + taxi.getCar(1).getModel()) + " id: " + taxi.getCar(1).getId()
                + " fuel: " + taxi.getCar(1).getFuelconsumption() + " l/100km");
    }
}
