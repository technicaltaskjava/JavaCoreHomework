package com.epam.task4;

import com.epam.task4.model.Car;

/**
 * Created by Olga Kramska on 02-Apr-16.
 */
public interface IParking {

    boolean park(Car car);

    boolean leaveParking(Car car);

    int findCar(Car car);

    int countFreePlaces();
}