package com.epam.task.one.airline;

import com.epam.task.one.planes.AirlinePlane;
import com.epam.task.one.planes.Plane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Airline {
    private List<AirlinePlane> airlinePlaneList;

    public Airline() {
        this.airlinePlaneList = new ArrayList<>();
    }

    public List<AirlinePlane> getAirlinePlaneList() {
        return airlinePlaneList;
    }

    public int getNumberOfPlanes(){
        return this.airlinePlaneList.size();
    }

    public void addPlane(AirlinePlane currentPlane) {
        this.airlinePlaneList.add(currentPlane);
    }

    public void deletePlane(String planeId) {
        if (this.airlinePlaneList.indexOf(planeId) != -1) {
            this.airlinePlaneList.remove(this.airlinePlaneList.indexOf(planeId));
        }
    }

    private int countTotalCapacity(List<AirlinePlane> currentAirlineList, String planeType) {
        int totalCapacity = 0;
        for (AirlinePlane currentPlane : currentAirlineList) {
            if (currentPlane.getPlaneType().equals(planeType)) {
                totalCapacity += currentPlane.getPlaneCapacity();
            }
        }
        return totalCapacity;
    }

    public int countTotalCargoCapacity() {
        return countTotalCapacity(this.airlinePlaneList, "Cargo");
    }

    public int countTotalPeopleCapacity() {
        return countTotalCapacity(this.airlinePlaneList, "Airbus");
    }

    public Plane[] sortPlaneRange() {
        Plane[] sortedAirline = new Plane[this.airlinePlaneList.size()];
        System.arraycopy(this.airlinePlaneList.toArray(), 0, sortedAirline, 0, this.airlinePlaneList.size());
        Arrays.sort(sortedAirline);
        return sortedAirline;
    }
}

