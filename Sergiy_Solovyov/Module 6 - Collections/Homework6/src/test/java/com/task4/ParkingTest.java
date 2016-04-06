package com.task4;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParkingTest {

    private Parking parking = new Parking(10);
    private Car car = new Car("AA3696FE", "Bob");
    private Car car2 = new Car("PP9966", "John");
    private Car car3 = new Car("IO8959LL", "Alex");

    @Before
    public void testGetFreePlaces(){
    assertEquals("Must be 10 places", parking.getFreePlaces(), 10);
}

    @Before
    public void testParkCar(){
        Car car = new Car("AA3696FE", "Bob");
        Car car2 = new Car("PP9966", "John");
        Car car3 = new Car("IO8959LL", "Alex");
        parking.parkCar(car);
        parking.parkCar(car2);
        parking.parkCar(car3);
        assertEquals("Must be 7 places", parking.getFreePlaces(), 7);
    }

    @Test
    public void testGetPlaceNumber() throws NoSuchCarException {
        assertEquals("Must be 10 places", parking.getPlaceNumber(car), 1);
    }

    @Test(expected = NoSuchCarException.class)
    public void testGetPlaceNumberException() throws NoSuchCarException {
        Car car4 = new Car("IO8959LL", "Arni");
        parking.getPlaceNumber(car4);
    }

    @Test(expected = NoSuchCarException.class)
    public void testLeaveParkingException() throws NoSuchCarException {
        Car car4 = new Car("IO8959LL", "Arni");
        parking.leaveParking(car4);
    }
}
