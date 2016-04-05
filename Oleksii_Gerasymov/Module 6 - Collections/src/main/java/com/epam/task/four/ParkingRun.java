package com.epam.task.four;

public class ParkingRun {

    private ParkingRun() {
    }

    public static void mainMenu() {
        String testCar = "AE1231AE";
        Parking testParking = new Parking(8);
        if (testParking.getNumberOfFreePlaces() >= 5) {
            testParking.newClient(testCar, 3);
            testParking.newClient("AE1232AE", 3);
            testParking.newClient("AE1233AE", 4);
            testParking.newClient("AE1234AE", 1);
            testParking.newClient("AE1235AE", 7);
        }
        if (testParking.isFreePlaces()) {
            testParking.newClient("AE1236AE", 7);
        }
        else {
            System.out.println("Sorry. We have not any free place.");
        }

        printParking(testParking);

        testParking.removeClient(5);
        System.out.println("Place number 5 is free now");

        if (!"FREE".equals(testParking.getCarNumberByPlace(6))) {
            System.out.println("there is a car number " + testParking.getCarNumberByPlace(6));
        }
        else {
            System.out.println("OMG! It is a joke! There is not any car!");
        }
        System.out.println("Busy places are : ");
        System.out.println(testParking.getBusyPlaces());
        System.out.println("Free places are : ");
        System.out.println(testParking.getFreePlaces());

        if (testParking.getPlaceByCarNumber(testCar) != -1) {
            System.out.println("Your car is now on place " + testParking.getPlaceByCarNumber("AE1231AE"));
        }
        else {
            System.out.println("OMG! It is a joke again! I do not see your car!");
        }
        System.out.println();
    }

    private static void printParking(Parking testParking) {
        System.out.println("Total number of places is : " + testParking.getLength());
        System.out.println("Free places : " + testParking.getNumberOfFreePlaces() +
                " Busy places : " + testParking.getNumberOfBusyPlaces());
        System.out.println(testParking.getParking());
        System.out.println();
    }
}
