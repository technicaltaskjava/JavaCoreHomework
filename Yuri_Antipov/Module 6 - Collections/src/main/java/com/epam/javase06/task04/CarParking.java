package com.epam.javase06.task04;

import java.util.Arrays;

public class CarParking {
    int[] places = new int [16]; // 0 = free place, 1 = not free place

    public String findFirstFreePlace() {
        int freePlace = 1;
        for (int i = 0; i < places.length; i++) {
            if (places[i] == 0) {
                freePlace = i + 1;
                break;
            }
        }
        return "Place #" + freePlace  + " is free.";
    }

    public String countFreePlaces() {
        int freePlaceCounter = 0;
        for (int i = 0; i < places.length; i++) {
            if (places[i] == 0) {
                freePlaceCounter++;
            }
        }
        return freePlaceCounter + " is (are) free.";
    }

    public String releasePlace(int placeNumber) {
        if (placeNumber < places.length && placeNumber > 0 && places[placeNumber] == 1) {
            places[placeNumber] = 0;
            return "The place #" + (places[placeNumber] + 1) + " has already released.";
        } else {
            return "The place #" + (places[placeNumber] + 1) + " is still free.";
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(places);
    }
}
