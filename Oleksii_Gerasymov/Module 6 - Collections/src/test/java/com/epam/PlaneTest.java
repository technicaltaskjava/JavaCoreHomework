package com.epam;

import com.epam.task.one.airline.Airline;
import com.epam.task.one.planes.AirlinePlane;
import com.epam.task.one.planes.Plane;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class PlaneTest {

        @Test
        public void airlineTest() {
            Airline airline = new Airline();

            AirlinePlane currentPlane = new AirlinePlane("board1221", "A310", "Airbus", 4000, 200);
            airline.addPlane(currentPlane);
            currentPlane = new AirlinePlane("board1222", "Boeing 747", "Airbus", 5000, 250);
            airline.addPlane(currentPlane);
            currentPlane = new AirlinePlane("board342", "Hercules C130", "Cargo", 5200, 300);
            airline.addPlane(currentPlane);
            currentPlane = new AirlinePlane("board21", "AN225 Mriya", "Cargo", 3000, 600);
            airline.addPlane(currentPlane);
            currentPlane = new AirlinePlane("board333", "AN24", "Airbus", 1000, 50);
            airline.addPlane(currentPlane);
            currentPlane = new AirlinePlane("board123", "AN174", "Cargo", 2000, 50);
            airline.addPlane(currentPlane);

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
