package com.epam.task.four;

public class Parking {
    String[] parkingPlace;

    public Parking() {
        parkingPlace = new String[10];
        openParking();
    }

    public Parking(int parkingSize) {
        parkingPlace = new String[parkingSize];
        openParking();
    }

    private void openParking() {
        for (int indexArray = 0; indexArray < parkingPlace.length; indexArray++) {
            parkingPlace[indexArray] = "FREE";
        }
    }

    public int getLength() {
        return parkingPlace.length;
    }

    public String getParking() {
        String result = "";
        for (int indexArray = 0; indexArray < parkingPlace.length; indexArray++) {
            result += (indexArray + 1) + " " + parkingPlace[indexArray] + System.lineSeparator();
        }
        return result;
    }

    public int getNumberOfFreePlaces() {
        int result = 0;
        for(String currentPlace : parkingPlace) {
            if ("FREE".equals(currentPlace)) {
                result ++;
            }
        }
        return result;
    }

    public boolean isFreePlaces() {
        boolean result = false;
        for(String currentPlace : parkingPlace) {
            if ("FREE".equals(currentPlace)) {
                result = true;
            }
        }
        return result;
    }

    public String getFreePlaces() {
        String result = "";
        for (int indexArray = 0; indexArray < parkingPlace.length; indexArray++) {
            if ("FREE".equals(parkingPlace[indexArray])) {
                result += (indexArray + 1) + " " + parkingPlace[indexArray] + System.lineSeparator();
            }
        }
        return result;
    }

    public int getNumberOfBusyPlaces() {
        int result = 0;
        for(String currentPlace : parkingPlace) {
            if (!"FREE".equals(currentPlace)) {
                result ++;
            }
        }
        return result;
    }

    public String getBusyPlaces() {
        String result = "";
        for (int indexArray = 0; indexArray < parkingPlace.length; indexArray++) {
            if (!"FREE".equals(parkingPlace[indexArray])) {
                result += (indexArray + 1) + " " + parkingPlace[indexArray] + System.lineSeparator();
            }
        }
        return result;
    }

    public int newClient(String carNumber, int entrancePlace) {
        int result = -1;
        for (int indexArray = entrancePlace-1; indexArray < parkingPlace.length; indexArray++) {
            if ("FREE".equals(parkingPlace[indexArray])) {
                result = indexArray;
                parkingPlace[indexArray] = carNumber;
                return result;
            }
        }
        if (result == -1) {
            for (int indexArray = 0; indexArray < parkingPlace.length - entrancePlace - 1; indexArray++) {
                if ("FREE".equals(parkingPlace[indexArray])) {
                    result = indexArray;
                    parkingPlace[indexArray] = carNumber;
                    return result;
                }
            }
        }
        return result;
    }

    public void removeClient(int entrancePlace) {
        parkingPlace[entrancePlace-1] = "FREE";
    }

    public String getCarNumberByPlace(int entrancePlace) {
        return parkingPlace[entrancePlace-1];
    }

    public int getPlaceByCarNumber(String carNumber) {
        int result = -1;
        for (int indexArray = 0; indexArray < parkingPlace.length; indexArray++) {
            if (carNumber.equals(parkingPlace[indexArray])) {
                result = indexArray+1;
            }
        }
        return result;
    }
}
