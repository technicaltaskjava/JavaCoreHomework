package com.epam.task.one.airline;

import com.epam.task.one.planes.AirlinePlane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class AirlineOperation {

    private AirlineOperation() {
    }

    public static void airLineCreate (Airline airline, String iniFile) throws FileNotFoundException {
        Scanner scanCreationIniFile = new Scanner(new FileInputStream(iniFile));
        int numberOfPlanes = Integer.parseInt(scanCreationIniFile.nextLine());
        for (int planeIndex = 0; planeIndex < numberOfPlanes; planeIndex++) {
            String currentPlaneId = scanCreationIniFile.nextLine();
            String currentPlaneModel = scanCreationIniFile.nextLine();
            String currentPlaneType = scanCreationIniFile.nextLine();
            int currentPlaneRange = Integer.parseInt(scanCreationIniFile.nextLine());
            int currentCapacity = Integer.parseInt(scanCreationIniFile.nextLine());

            AirlinePlane currentPlane = new AirlinePlane(currentPlaneId, currentPlaneModel,
                        currentPlaneType, currentPlaneRange, currentCapacity);
                airline.addPlane(currentPlane);
        }
        scanCreationIniFile.close();
    }

    /*
    Ini-file contents desired values for search for each field of Plane object.
    If the field is numeric at findPlane.ini we define two values - beginning and end of desired range.
    If the field is String we defined desired value as String variable or type "any" for any value.
    During reading from ini-file we create two objects of Plane class which determine lower and upper ends
    of field ranges for each object. Comparing each field of Plane object with
    that lower and upper ends (equals or "any" for String fields) we can fully determine accordance of the current plane
    to desired arguments range.
    */

    public static Airline findPlanes(Airline airline, String iniFile) throws FileNotFoundException {
        Airline filteredAirline = airline;
        Scanner scanIniFile = new Scanner(new FileInputStream(iniFile));
        String currentPlaneId = scanIniFile.nextLine();
        String currentPlaneModel = scanIniFile.nextLine();
        String currentPlaneType = scanIniFile.nextLine();
        int currentLowerPlaneRange = scanIniFile.nextInt();
        int currentUpperPlaneRange = scanIniFile.nextInt();
        int currentLowerPlaneCapacity = scanIniFile.nextInt();
        int currentUpperPlaneCapacity = scanIniFile.nextInt();
        scanIniFile.close();

        AirlinePlane minPlane = new AirlinePlane(currentPlaneId, currentPlaneModel, currentPlaneType,
                currentLowerPlaneRange, currentLowerPlaneCapacity);
        AirlinePlane maxPlane = new AirlinePlane(currentPlaneId, currentPlaneModel, currentPlaneType,
                currentUpperPlaneRange, currentUpperPlaneCapacity);

        Iterator<AirlinePlane> planeIterator = filteredAirline.getAirlinePlaneList().iterator();
        while (planeIterator.hasNext()) {
            AirlinePlane currentPlane = planeIterator.next();
            if (!(checkStringFields(currentPlane, minPlane) &&
                    checkNumberFields(currentPlane, minPlane, maxPlane))) {
                planeIterator.remove();
            }
        }
        return filteredAirline;
    }

    private static boolean checkStringFields(AirlinePlane currentPlane, AirlinePlane lowerFilterPlane) {
        String any = "any";
        if (!(lowerFilterPlane.getPlaneId().equals(any)||
                currentPlane.getPlaneId().equals(lowerFilterPlane.getPlaneId()))) {
            return false;
        }
        if (!(lowerFilterPlane.getPlaneModel().equals(any)||
                currentPlane.getPlaneModel().equals(lowerFilterPlane.getPlaneModel()))) {
            return false;
        }
        if (!(lowerFilterPlane.getPlaneType().equals(any)||
                currentPlane.getPlaneType().equals(lowerFilterPlane.getPlaneType()))) {
            return false;
        }
        return true;
    }

    private static boolean checkNumberFields(AirlinePlane currentPlane, AirlinePlane lowerFilterPlane,
                                             AirlinePlane upperFilterPlane) {
        if ((currentPlane.getPlaneRange() < (lowerFilterPlane.getPlaneRange())) ||
                (currentPlane.getPlaneRange() > (upperFilterPlane.getPlaneRange()))) {
            return false;
        }
        if ((currentPlane.getPlaneCapacity() < (lowerFilterPlane.getPlaneCapacity())) ||
                (currentPlane.getPlaneCapacity() > (upperFilterPlane.getPlaneCapacity()))) {
            return false;
        }
        return true;
    }

}