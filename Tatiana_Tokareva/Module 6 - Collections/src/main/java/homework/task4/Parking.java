package homework.task4;

import java.util.Arrays;

public class Parking {
    private static final int DEFAULT_CAPACITY = 10;
    private String[] parking;

    public Parking(int capacity) {
        if (capacity < 1) {
            parking = new String[DEFAULT_CAPACITY];
        } else {
            parking = new String[capacity];
        }
    }

    public Parking() {
        this(DEFAULT_CAPACITY);
    }

    //add car in place
    public boolean add(String carName) {
        return add(0, carName);
    }

    public boolean add(int position, String carName) {
        if (position < 0 || position >= parking.length || carName == null) {
            return false;
        }

        for (int index = position; index < parking.length; index++) {
            if (setCar(carName, index)) {
                return true;
            }
        }

        for (int index = 0; index < position; index++) {
            if (setCar(carName, index)) {
                return true;
            }
        }

        return false;
    }

    private boolean setCar(String carName, int index) {
        if (parking[index] == null) {
            parking[index] = carName;
            return true;
        }
        return false;
    }

    //remove car
    public boolean remove(int position) {
        if (position < 0 || position >= parking.length) {
            return false;
        }

        for (int index = 0; index < parking.length; index++) {
            if (position == index) {
                parking[index] = null;
                return true;
            }
        }
        return false;
    }

    public boolean remove(String carName) {
        if (carName == null) {
            return false;
        }
        for (int index = 0; index < parking.length; index++) {
            if (carName.equals(parking[index])) {
                parking[index] = null;
                return true;
            }
           }
        return false;
    }

    //what place of car
    public int placeOfCar(String carName) {
        for (int index = 0; index < parking.length; index++) {
            if (carName.equals(parking[index])) {
                return index;
            }
        }
        return -1;
    }

    //free count places
    public int freeCountPlaces(int size, int countPlaces) {
        return size - countPlaces;

    }

    //total count places


    public int size() {
        return parking.length;
    }
    //not free count places
    public int countPlaces() {
        int count = 0;
        for (String aParking : parking) {
            if (aParking != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parking=" + Arrays.toString(parking) +
                '}';
    }
}
