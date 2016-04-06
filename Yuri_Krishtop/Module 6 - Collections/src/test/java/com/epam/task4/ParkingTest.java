package com.epam.task4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yuriy Krishtop on 04.04.2016.
 */
public class ParkingTest {

    @Test
    public void parkingTest() {
        Parking parking = new Parking(7);
        parking.carComing(2112);
        parking.carComing(9820);
        parking.carComing(4786);
        assertEquals(4, parking.getFreePlaces());
        parking.carLeaving(9820);
        assertTrue(parking.isEmptyPlace(1));
        assertEquals(5, parking.getFreePlaces());
        assertEquals(2, parking.getNumPlace(4786));
        assertEquals(2112, parking.getCarNumbersOnParking()[0]);
        parking.carComing(5692);
        assertTrue(!parking.isEmptyPlace(1));
    }
}
