package com.epam.task4;

import com.epam.task4.model.Car;
import com.epam.task4.model.Place;

/**
 * Created by Olga Kramska on 02-Apr-16.
 */
public class Parking implements IParking {
    private Place[] places;
    private int freePlaces;

    public Parking(int numberOfPlaces) {
        places = new Place[numberOfPlaces];
        for (int i = 0; i < numberOfPlaces; i++) {
            places[i] = new Place();
        }
        freePlaces = numberOfPlaces;
    }

    @Override
    public boolean park(Car car) {
        if (freePlaces > 0) {
            for (Place place : places) {
                if (place.getCar() == null) {
                    place.setCar(car);
                    freePlaces--;
                    break;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean leaveParking(Car car) {
        for (Place place : places) {
            if (car.equals(place.getCar())) {
                place.setCar(null);
                freePlaces++;
                return true;
            }
        }
        return false;
    }

    @Override
    public int findCar(Car car) {
        int carPlace = -1;
        for (int i = 0; i < places.length; i++) {
            if (car.equals(places[i].getCar())) {
                carPlace = i;
                break;
            }
        }
        return carPlace;
    }

    @Override
    public int countFreePlaces() {
        return freePlaces;
    }
}
