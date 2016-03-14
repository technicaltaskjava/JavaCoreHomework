import java.io.BufferedReader;
import java.io.InputStreamReader;

import extras.Materials;
import extras.Weights;
import management.*;


public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        System.out.println("Welcome to the shop!");
        EquipmentShop.initialize();
        int input = -1;
        do{
        //Main menu.
            System.out.println("What would you like to do?");
            System.out.println("1 - Shop options.");
            System.out.println("2 - Knight options.");
            System.out.println("0 - Exit.");
            input = intInput();
            switch(input){
                case 0:
                    break;
                case 1:
                //Shop options
                    do {
                        System.out.println("Shop options:");
                        System.out.println("1 - Show all inventory.");
                        System.out.println("2 - Sort inventory by price.");
                        System.out.println("3 - Sort inventory by name.");
                        System.out.println("4 - Search in names.");
                        System.out.println("5 - Search by value range.");
                        System.out.println("0 - Go to main menu.");
                        input = intInput();
                        switch(input){
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
                                System.out.println("No such menu item, try again.");
                        }
                    } while (input != 0);
                    input = -1;
                    break;
                //End of shop options
                case 2:
                //Knight options
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
                        switch(input){
                            case 0:
                                break;
                            case 1: // Show current equipment.
                                Knight.printEquipment();
                                break;
                            case 2: // Show total value.
                                if(Knight.getTotalValue() == 0){
                                    System.out.println("Equip your knight first.");
                                } else{
                                    System.out.println("Total value of equipment is " + Knight.getTotalValue());
                                }
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
                                System.out.println("No such menu item, try again.");
                        }
                    } while (input != 0);
                    input = -1;
                    break;
                //End of knight options
                default:
                    System.out.println("No such menu item, try again.");
            }
        //End of main menu.
        } while (input != 0);
    }

    static void createEquipmentPiece(int option){
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
            }
        } else {
            System.out.println("Incorrect input, equipment was not created.");
        }
    }

    static String validatedInput(){
        String buffer = null;
        try{
            buffer = reader.readLine();
        } catch(Throwable IOException){
            System.out.println("Input error.");
            buffer = null;
        } finally{
            return buffer;
        }
    }

    static String materialInput(){
        System.out.println("Enter needed material (bronze, steel, silver, golden):");
        String buffer = validatedInput().toLowerCase();
        if (checkMaterials(buffer) == false){
            buffer = null;
        }
        return buffer;
    }

    static String weightInput(){
        System.out.println("Enter needed weight (light, medium, heavy):");
        String buffer = validatedInput().toLowerCase();
        if (checkWeights(buffer) == false){
            buffer = null;
        }
        return buffer;
    }

    public static boolean checkMaterials(String material){
        for (int i = 0; i < Materials.values().length; i++){
            if(material.equalsIgnoreCase(Materials.values()[i].toString())){
                return true;
            }
        }
        return false;
    }

    public static boolean checkWeights(String weight){
        for (int i = 0; i < Weights.values().length; i++){
            if(weight.equalsIgnoreCase(Weights.values()[i].toString())){
                return true;
            }
        }
        return false;
    }

    static float stringToFloat(String input){
        float buffer = -1;
        try {
            buffer = Float.valueOf(input);
        } catch(Throwable NumberFormatException) {
            System.out.println("Input is not a number.");
        } finally {
            return buffer;
        }
    }

    static int stringToInt(String input){
        int buffer = -1;
        try {
            buffer = Integer.valueOf(input);
        } catch(Throwable NumberFormatException) {
            System.out.println("Input is not a number.");
        } finally {
            return buffer;
        }
    }

    static int intInput(){
        return stringToInt(validatedInput());
    }

    static float floatInput(){
        return stringToFloat(validatedInput());
    }
}
