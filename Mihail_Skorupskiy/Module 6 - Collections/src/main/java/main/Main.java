package main;

import knightgear.extras.Materials;
import knightgear.extras.Weights;
import knightgear.management.EquipmentShop;
import knightgear.management.Knight;
import parking.Car;
import parking.Parking;
import structures.MyArrayList;
import structures.NumberList;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private Main(){}

    public static void main(String[] args) {
        //Careful, a lot of System.out.println() is inbound!
        //If you don't want to read dozens of lines from console - don't launch it, just look at the code :)

        taskOne();
        taskTwo();
        taskThree();
        taskFour();
    }

    static void taskOne(){
        System.out.println("\nTask 1.");
        System.out.println("Welcome to the shop!");
        EquipmentShop.initialize();
        TaskOne.mainMenu();
    }

    static void taskTwo(){
        System.out.println("\nTask 2.");
        Integer[] source = {1, 2, 3, 4, 5};
        System.out.println("Source array: \n" + Arrays.toString(source));
        MyArrayList<Integer> arrayList = new MyArrayList<>(source);
        System.out.println("Initial list contents: \n" + arrayList);
        arrayList.add(7, 7);
        System.out.println("List contents after adding an element out of bounds: \n" + arrayList);
        arrayList.trim();
        System.out.println("List contents after trimming: \n" + arrayList);
        arrayList.addArray(source);
        System.out.println("List contents after appending source array: \n" + arrayList);
        System.out.println("List size is now " + arrayList.size());
        arrayList.remove(3);
        System.out.println("Element number four: " + arrayList.get(4));
        System.out.println("List contents after removing element 3: \n" + arrayList);
        System.out.println("Extracted array: \n" + Arrays.toString(arrayList.toArray()));
        arrayList.clear();
        System.out.println("Cleared list contents: \n" + arrayList);
    }

    static void taskThree(){
        System.out.println("\nTask 3.");
        Double[] source2 = {0.5, 0.5, 0.5, 0.5, 0.25, 0.5, 0.75, 0.5, 0.25, 0.5, 0.75, 0.5};
        NumberList<Double> numbers = new NumberList<>(source2);
        System.out.println("List contents: " + numbers);
        numbers.removeByPosition(5);
        System.out.println("After deleting 5th element: " + numbers);
        numbers.removeByValue(0.5);
        System.out.println("After deleting all 0.5 values: " + numbers);
        numbers.compress();
        System.out.println("After compressing: " + numbers);
        numbers.increaseSize(1);
        System.out.println("After increasing size by 1: " + numbers);
        numbers.add(0.5, numbers.size()-1);
        System.out.println("After adding new 0.5 element in the end: " + numbers);
        System.out.println("Searching for 0.25: " + numbers.searchByValue(0.25));
        System.out.println("Searching for 0.7: " + numbers.searchByValue(0.7));
        System.out.println("Searching for 0.4: " + numbers.searchByValue(0.4));
        System.out.println("Searching for 1: " + numbers.searchByValue(1d));
    }

    static void taskFour(){
        System.out.println("\nTask 4.");
        Parking parking = new Parking(10);
        Car alfaRomeo = new Car("White Alfa Romeo");
        Car buick = new Car("Black Buick");
        Car plymouth = new Car("Purple Plymouth");
        alfaRomeo.park(parking, 5);
        buick.park(parking, 4);
        plymouth.park(parking, 4);
        System.out.println("Parking size: " + parking.size());
        System.out.println("Positions of free spots: " + Arrays.toString(parking.freeSpotsPositions()));
        System.out.println("Car on position 4: " + parking.getCar(4));
        System.out.println("Car on position 5: " + parking.getCar(5));
        System.out.println("Car on position 6: " + parking.getCar(6));
        buick.driveOut(parking);
        parking.placeCar(buick, 1);
        parking.removeCar(alfaRomeo);
        plymouth.park(parking, 3);
        parking.placeCar(alfaRomeo, 1);
        System.out.println("Positions of free spots after: " + Arrays.toString(parking.freeSpotsPositions()));
        System.out.println("Buick is now on position: " + parking.findCar(buick));
        System.out.println("Parking condition: " + parking);
    }
}

class TaskOne {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static Logger logger = Logger.getLogger("main.TaskOne");

    private TaskOne(){}

    static void mainMenu(){
        int input;
        do {
            System.out.println("What would you like to do?");
            System.out.println("1 - Shop options.");
            System.out.println("2 - Knight options.");
            System.out.println("0 - Exit.");
            input = intInput();
            switch (input) {
                case 0:
                    break;
                case 1:
                    shopOptions();
                    input = -1;
                    break;
                case 2:
                    knightOptions();
                    input = -1;
                    break;
                default:
                    System.out.println("No such menu item, try again.");
            }
        } while (input != 0);
    }

    static void shopOptions(){
        int input;
        do {
            System.out.println("Shop options:");
            System.out.println("1 - Show all inventory.");
            System.out.println("2 - Sort inventory by price.");
            System.out.println("3 - Sort inventory by name.");
            System.out.println("4 - Search in names.");
            System.out.println("5 - Search by value range.");
            System.out.println("0 - Go to main menu.");
            input = intInput();
            switch (input) {
                case 0:
                    break;
                case 1: // Show all inventory
                    EquipmentShop.show();
                    break;
                case 2: // Sort inventory by price
                    EquipmentShop.sortByValue();
                    break;
                case 3: // Sort inventory by name
                    EquipmentShop.sortByName();
                    break;
                case 4: // Search in names
                    System.out.println("Enter any sequence of characters to be found in names:");
                    EquipmentShop.searchInName(validatedInput());
                    break;
                case 5: // Search by value range
                    System.out.println("Enter two numbers to limit search range:");
                    EquipmentShop.searchByValueRange(floatInput(), floatInput());
                    break;
                default:
                    System.out.println("Wrong menu item, try again.");
            }
        } while (input != 0);
    }

    static void knightOptions(){
        int input;
        do {
            System.out.println("Knight options:");
            System.out.println("1 - Show current equipment.");
            System.out.println("2 - Show total value of currently equipped items.");
            System.out.println("3 - Choose a new sword.");
            System.out.println("4 - Choose a new armor.");
            System.out.println("5 - Choose a new shield.");
            System.out.println("6 - Unequip all.");
            System.out.println("0 - Go to main menu.");
            input = intInput();
            switch (input) {
                case 0:
                    break;
                case 1: // Show current equipment.
                    Knight.printEquipment();
                    break;
                case 2: // Show total value.
                    if (Double.compare(Knight.getTotalValue(), 0) == 0) {
                        System.out.println("Equip your knight first.");
                    } else System.out.println("Total value of equipment is " + Knight.getTotalValue());
                    break;
                case 3: // Equip a sword.
                    createEquipmentPiece(input);
                    break;
                case 4: // Equip armor.
                    createEquipmentPiece(input);
                    break;
                case 5: // Equip a shield.
                    createEquipmentPiece(input);
                    break;
                case 6: // Unequip all.
                    Knight.reset();
                    break;
                default:
                    System.out.println("Incorrect menu item, try again.");
            }
        } while (input != 0);
    }

    static void createEquipmentPiece(int option) {
        String material = materialInput();
        String weight = weightInput();
        if (material != null && weight != null) {
            switch (option) {
                case 3:
                    Knight.setMainHand(material, weight);
                    break;
                case 4:
                    Knight.setArmor(material, weight);
                    break;
                case 5:
                    Knight.setOffHand(material, weight);
                    break;
                default: break;
            }
        } else {
            System.out.println("Incorrect input, equipment was not created.");
        }
    }

    static String validatedInput() {
        String buffer = null;
        try {
            buffer = reader.readLine();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Input error", e);
        }
        return buffer;
    }

    static String materialInput() {
        System.out.println("Enter needed material (bronze, steel, silver, golden):");
        String buffer = validatedInput().toLowerCase();
        if (!checkMaterials(buffer)) {
            buffer = null;
        }
        return buffer;
    }

    static String weightInput() {
        System.out.println("Enter needed weight (light, medium, heavy):");
        String buffer = validatedInput().toLowerCase();
        if (!checkWeights(buffer)) {
            buffer = null;
        }
        return buffer;
    }

    public static boolean checkMaterials(String material) {
        for (int i = 0; i < Materials.values().length; i++) {
            if (material.equalsIgnoreCase(Materials.values()[i].toString())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkWeights(String weight) {
        for (int i = 0; i < Weights.values().length; i++) {
            if (weight.equalsIgnoreCase(Weights.values()[i].toString())) {
                return true;
            }
        }
        return false;
    }

    static float stringToFloat(String input) {
        float buffer = -1;
        try {
            buffer = Float.valueOf(input);
        } catch (NumberFormatException e) {
            System.out.println("Input is not a number.");
        }
        return buffer;
    }

    static int stringToInt(String input) {
        int buffer = -1;
        try {
            buffer = Integer.valueOf(input);
        } catch (NumberFormatException e) {
            System.out.println("Input is not a number.");
        }
        return buffer;
    }

    static int intInput() {
        return stringToInt(validatedInput());
    }

    static float floatInput() {
        return stringToFloat(validatedInput());
    }

}
