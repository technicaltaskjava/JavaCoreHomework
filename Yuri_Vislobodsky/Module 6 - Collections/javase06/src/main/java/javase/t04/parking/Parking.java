package javase.t04.parking;

import java.util.Arrays;

/**
 * Parking Class
 * Created by Yury Vislobodsky on 02.04.2016.
 */
public class Parking {
    private Car[] space;

    public Parking(int capacity) {
        space = new Car[capacity];
    }

    public int arrival(Car car, int placeId) {
        for (int index = placeId; index < space.length; index++) {
            if (space[index] == null) {
                space[index] = car;
                return index;
            }
        }
        return -1;
    }

    public Car departure(int placeId) {
        Car car = getCar(placeId);
        if (car != null) {
            space[placeId] = null;
        }
        return car;
    }

    public int getFreePlacesCount() {
        int count = 0;
        for (Car car : space) {
            if (car == null) {
                count ++;
            }
        }
        return count;
    }

    public int searchCar(Car car) {
        for (int index = 0; index < space.length; index++) {
            if ((space[index] != null) && (space[index].equals(car))) {
                return index;
            }
        }
        return -1;
    }

    public Car getCar(int placeId) {
        if (placeId < 0 || placeId >= space.length) {
            return null;
        }
        return space[placeId];
    }

    @Override
    public String toString() {
        return Arrays.toString(space);
    }
}
