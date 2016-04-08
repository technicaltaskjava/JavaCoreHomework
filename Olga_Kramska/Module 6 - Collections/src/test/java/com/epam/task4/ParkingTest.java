package com.epam.task4;

import com.epam.task4.model.Car;
import com.epam.task4.type.Brand;
import com.epam.task4.type.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Olga Kramska on 03-Apr-16.
 */
public class ParkingTest {

    private IParking parking;
    private Car car1;
    private Car car2;

    @Before
    public void init() {
        parking = new Parking(10);
        car1 = new Car(Brand.BMW, Color.BLACK, "123456");
        car2 = new Car(Brand.CHEVROLET, Color.WHITE, "09876");
    }

    @Test
    public void testPark() {
        assertTrue(parking.park(car1));
        assertEquals(9, parking.countFreePlaces());
    }

    @Test
    public void testFindCar() {
        parking.park(car1);
        assertEquals(0, parking.findCar(car1));
        assertEquals(-1, parking.findCar(car2));
    }

    @Test
    public void testLeaveParking() {
        parking.park(car1);
        parking.park(car2);
        assertEquals(8, parking.countFreePlaces());
        assertTrue(parking.leaveParking(car1));
        assertEquals(9, parking.countFreePlaces());
    }
}
