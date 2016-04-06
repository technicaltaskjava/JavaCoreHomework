package homework.task4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private Main() {
    }

    public static void main(String[] args) {
        System.out.print("Need parking capacity ");
        int capacity = getValue();
        Parking parking = new Parking(capacity);
        System.out.print("Position arriving BMW ");
        int position = getValue();
        parking.add(position, "BMW");
        parking.add("AUDI");
        parking.add("MERCEDES");
        parking.add("MAZDA");

        System.out.println("Parking after arriving car:\n"+ parking);

        System.out.print("Position to remove ");
        int positionToRemove = getValue();
        parking.remove(positionToRemove);
        parking.remove("MAZDA");
        System.out.println("Parking after car left from position "+positionToRemove+"and arriving MAZDA\n"+ parking);

        int placeOfCar = parking.placeOfCar("MERCEDES");
        System.out.println(String.format("Place of MERCEDES is:%s",placeOfCar));

        int countPlaces = parking.countPlaces();
        System.out.println(String.format("Quantity of not free places:%s", countPlaces));

        int size = parking.size();
        int freePlaces = parking.freeCountPlaces(size, countPlaces);
        System.out.println(String.format("Quantity of free places:%s", freePlaces));

        if (scanner != null) {
            scanner.close();
        }
    }

    private static int getValue() {
        try {
            System.out.println("enter value:");
            int capacity = scanner.nextInt();
            return capacity;
        } catch (InputMismatchException e) {
            System.out.println("Entered capacity incorrect. Create parking with default value");
            return -1;
        }
    }
}
