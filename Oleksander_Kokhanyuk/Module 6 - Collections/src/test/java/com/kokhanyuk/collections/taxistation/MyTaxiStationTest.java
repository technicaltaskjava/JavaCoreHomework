package com.kokhanyuk.collections.taxistation;

import com.kokhanyuk.collections.taxistation.cars.Car;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyTaxiStationTest {

    @Test
    public void testAddCar() throws Exception {
        MyTaxiStation taxi = new MyTaxiStation();
        taxi.addCar(new Car("Opel", 1500, 10));
        taxi.addCar(new Car("Opel", 1500, 10));
        taxi.addCar(new Car("Opel", 1500, 5));
        taxi.addCar(new Car("Mers", 1500, 8));
        assertEquals(taxi.getCar(4).getId(), 4);
        assertEquals(taxi.getCar(3).getFuelconsumption(), 5);
    }

    @Test
    public void testGetCar() throws Exception {
        MyTaxiStation taxi = new MyTaxiStation();
        taxi.addCar(new Car("Opel", 1500, 10));
        taxi.addCar(new Car("Opel", 1500, 10));
        taxi.addCar(new Car("Opel", 1500, 5));
        taxi.addCar(new Car("Mers", 1500, 8));
        assertFalse(taxi.getCar(1).equals(taxi.getCar(2)));
    }

    @Test
    public void testGetTotalCar() throws Exception {
        MyTaxiStation taxi = new MyTaxiStation();
        taxi.addCar(new Car("Opel", 1500, 10));
        taxi.addCar(new Car("Opel", 1500, 10));
        taxi.addCar(new Car("Opel", 1500, 5));
        taxi.addCar(new Car("Mers", 1500, 8));
        assertEquals(taxi.getTotalCar(), 4);
    }
}