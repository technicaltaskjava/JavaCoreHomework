package com.kokhanyuk.collections.parking;

import com.kokhanyuk.collections.taxistation.cars.Car;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class FastParkingTest {

    @Test
    public void testAddCar() throws Exception {
        FastParking park = new FastParking(50);
        for (int i = 0; i < 50; i++) {
            park.addCar(i, new Car());
        }
        assertEquals(park.getFreePlace(), 0);
    }

    @Test
    public void testRemCar() throws Exception {
        FastParking park = new FastParking(50);
        for (int i = 0; i < 50; i++) {
            park.addCar(i, new Car());
        }
        for (int i = 25; i < 50; i++) {
            park.remCar(i);
        }
        assertEquals(park.getFreePlace(), 25);
    }

    @Test
    public void testGetFreePlace() throws Exception {
        FastParking park = new FastParking(50);
        for (int i = 0; i < 50; i++) {
            park.addCar(i, new Car());
        }
        for (int i = 0; i < 50; i++) {
            park.remCar(i);
        }
        assertEquals(park.getFreePlace(), 50);
    }

    @Test
    public void testGetCarsOnParking() throws Exception {
        FastParking park = new FastParking(50);
        for (int i = 0; i < 50; i++) {
            park.addCar(i, new Car());
        }
        for (int i = 25; i < 35; i++) {
            park.remCar(i);
        }
        assertEquals(park.getCarsOnParking(), 40);
    }
}