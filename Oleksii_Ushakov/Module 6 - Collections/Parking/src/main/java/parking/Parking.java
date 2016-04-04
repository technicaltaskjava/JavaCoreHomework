package parking;

import car.Car;

import java.util.NoSuchElementException;

/**
 * @author Alexey Ushakov
 */
public class Parking {
    private Car[] parkingSpace;
    private int carCount;

    public Parking(int numberPlaces) {
        this.carCount = 0;
        this.parkingSpace = new Car[numberPlaces];
    }

    public int getFreePlacesCount() {
        return parkingSpace.length - carCount;
    }

    private boolean correctIndex(int index) {
        return (index >= 0) && (index < parkingSpace.length);
    }

    public int getNearSpace() {
        return getNearSpace(0);
    }

    public int getNearSpace(int from) {
        if (correctIndex(from)) {
            for (int i = from; i < parkingSpace.length; i++) {
                if (parkingSpace[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean isFreeSpace(int index) {
        if (correctIndex(index)) {
            return parkingSpace[index] == null;
        }
        return false;
    }

    public boolean carArrived(Car car) {
        return carArrived(0, car);
    }

    public boolean carArrived(int index, Car car) {
        int nearSpace = getNearSpace(index);
        if (nearSpace != -1) {
            parkingSpace[nearSpace] = car;
            carCount++;
            return true;
        } else {
            return false;
        }
    }

    public void carLeft(int index) {
        if (correctIndex(index)) {
            carCount--;
            parkingSpace[index] = null;
        } else {
            throw new ArrayIndexOutOfBoundsException("Wrong car index ".concat(String.valueOf(index)));
        }
    }

    public void carLeft(Car car) {
        boolean carNotFound = true;

        for (int i = 0; i < parkingSpace.length; i++) {
            if ((parkingSpace[i] != null) && (parkingSpace[i].equals(car))) {
                parkingSpace[i] = null;
                carNotFound = false;
                carCount--;
                break;
            }
        }

        if (carNotFound) {
            throw new NoSuchElementException("Car ".concat(car.toString()).concat(" not found"));
        }
    }

}
