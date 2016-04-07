package com.epam.task.one.airline;

import com.epam.task.one.planes.Plane;

import java.io.FileNotFoundException;

public class AirlineRun {
    private static final String CREATE_INI_FILE = "createAirline.ini";
    private static final String SEARCH_INI_FILE = "findPlane.ini";

    private AirlineRun() {
    }

    public static void mainMenu() throws FileNotFoundException {
        Airline airline = new Airline();
        AirlineOperation.airLineCreate(airline, CREATE_INI_FILE);

        System.out.println("Airline loaded. Total amount of planes is : " + airline.getNumberOfPlanes());
        System.out.println("Total people capacity of airline is : " + airline.countTotalPeopleCapacity());
        System.out.println("Total cargo capacity of airline is : " + airline.countTotalCargoCapacity());

        Plane[] resultAirline = airline.sortPlaneRange();
        System.out.format("%n%s%n", "Sorted airline by flight range:");
        outTableHead();
        for (int indexOfArray = 0; indexOfArray < resultAirline.length; indexOfArray++) {
            System.out.println(resultAirline[indexOfArray].outPlaneData());
        }
        System.out.println();

        System.out.println("Searching plane by parameters at ini-file.");
        Airline filteredAirline = AirlineOperation.findPlanes(airline, SEARCH_INI_FILE);
        if (filteredAirline.getNumberOfPlanes() > 0) {
            outTableHead();
            for (Plane currentArray : filteredAirline.getAirlinePlaneList()) {
                System.out.println(currentArray.outPlaneData());
            }
        }
        else {
            System.out.println("No any matches.");
        }
    }

    private static void outTableHead () {
        String formatOut = "%-20s";
        System.out.printf(formatOut, "Plane ID");
        System.out.printf(formatOut, "Plane model");
        System.out.printf(formatOut, "Plane type");
        System.out.printf(formatOut, "Plane flight range");
        System.out.printf(formatOut, "Plane capacity");
        System.out.println();
    }
}
