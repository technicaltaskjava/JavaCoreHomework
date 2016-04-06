package com.epam.task4;

/**
 * Created by Yuriy Krishtop on 03.04.2016.
 */
public class Parking {
    private int[] parkingPlaces;

    public Parking(int maxParkingPlaces) {
        parkingPlaces = new int[maxParkingPlaces];
    }

    public boolean carComing(int carNumber) {
        boolean park = false;
        for (int i = 0; i < parkingPlaces.length; i++) {
            if (parkingPlaces[i] == 0) {
                parkingPlaces[i] = carNumber;
                park = true;
                break;
            }
        }
        return park;
    }

    public boolean carLeaving(int carNumber) {
        boolean leaving = false;
        for (int i = 0; i < parkingPlaces.length; i++) {
            if (parkingPlaces[i] == carNumber) {
                parkingPlaces[i] = 0;
                leaving = true;
                break;
            }
        }
        return leaving;
    }

    public int getNumPlace(int carNumber) {
        int numPlace = -1;
        for (int i = 0; i < parkingPlaces.length; i++) {
            if (parkingPlaces[i] == carNumber) {
                numPlace = i;
            }
        }
        return numPlace;
    }

    public int getFreePlaces() {
        int freePlace = 0;
        for (int space : parkingPlaces) {
            if (space == 0) {
                freePlace++;
            }
        }
        return freePlace;
    }

    public int[] getCarNumbersOnParking() {
        int[] carNums;
        int countOccupiedPlaces = 0;
        for (int space : parkingPlaces) {
            if (space != 0) {
                countOccupiedPlaces++;
            }
        }
        if (countOccupiedPlaces > 0) {
            carNums = new int[countOccupiedPlaces];
            int i = 0;
            for (int place : parkingPlaces) {
                if (place != 0) {
                    carNums[i] = place;
                    i++;
                }
            }
        } else {
            carNums = new int[0];
        }
        return carNums;
    }

    public boolean isEmptyPlace(int numPlace) {
        if (numPlace > (parkingPlaces.length - 1)) {
            return false;
        } else {
            return parkingPlaces[numPlace] == 0;
        }
    }

}
