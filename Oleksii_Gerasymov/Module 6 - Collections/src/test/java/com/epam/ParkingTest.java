package com.epam;

import com.epam.task.four.Parking;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ParkingTest {
    @Test
    public void setTest() throws Exception {
        Parking testParking = new Parking(5);
        testParking.newClient("AE1232AE", 3);
        testParking.newClient("AE1233AE", 4);
        testParking.newClient("AE1234AE", 1);
        testParking.newClient("AE1235AE", 1);

        assertEquals(true, testParking.isFreePlaces());
        assertEquals(5, testParking.getLength());
        assertEquals(1, testParking.getNumberOfFreePlaces());
        assertEquals(4, testParking.getNumberOfBusyPlaces());
        assertEquals(2, testParking.getPlaceByCarNumber("AE1235AE"));
        assertEquals("AE1233AE", testParking.getCarNumberByPlace(4));
    }
}
