package com.epam;

import com.epam.task.one.airline.Airline;
import com.epam.task.one.airline.AirlineOperation;
import com.epam.task.one.planes.Plane;
import org.junit.Test;

import java.io.FileNotFoundException;

import static junit.framework.Assert.assertEquals;

public class PlaneTest {

        @Test
        public void airlineTest() {
            Airline airline = new Airline();
            try {
                AirlineOperation.airLineCreate(airline, "createAirline.ini");
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            assertEquals(6, airline.getNumberOfPlanes());
            assertEquals(500, airline.countTotalPeopleCapacity());
            assertEquals(950, airline.countTotalCargoCapacity());

            Plane[] resultAirline = airline.sortPlaneRange();
            int minValue = resultAirline[0].getPlaneRange();
            int maxValue = resultAirline[resultAirline.length-1].getPlaneRange();
            assertEquals(1000, minValue);
            assertEquals(5200, maxValue);
        }
}
