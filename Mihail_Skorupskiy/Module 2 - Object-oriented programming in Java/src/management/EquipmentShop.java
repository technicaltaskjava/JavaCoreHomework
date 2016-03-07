package management;

import equipment.pieces.Armor;
import equipment.pieces.Shield;
import equipment.pieces.Sword;
import equipment.Equipment;
import extras.*;


public class EquipmentShop {
    private static Equipment[] shop;

    public static void initialize() {
        //Generating an array containing all possible combinations of Materials and Weights for Sword, Armor and Shield.
        // %4 and /4 in indices represent Materials.values().length (longer of the two enums). Replaced by numbers to
        // keep length of code lines reasonable.
        int shopSize = (Materials.values().length) * (Weights.values().length) * 3;
        shop = new Equipment[shopSize];
        for (int i = 0; i < shopSize / 3; i++) {
            shop[i] = new Sword(Materials.values()[i % 4].toString(), Weights.values()[i / 4].toString());
            shop[i + (shopSize / 3)] = new Armor(Materials.values()[i % 4].toString(), Weights.values()[i / 4].toString());
            shop[i + (shopSize / 3 * 2)] = new Shield(Materials.values()[i % 4].toString(), Weights.values()[i / 4].toString());
        }
    }

    public static void show() {
        for (int i = 0; i < shop.length; i++) {
            System.out.print(i + 1 + ". ");
            shop[i].print();
        }
    }

    public static void sortByValue() {
        //Standard (and terrible) bubble-sort
        for (int i = 0; i < shop.length - 1; i++) {
            for (int j = 0; j < shop.length - i - 1; j++) {
                if (shop[j].getValue() > shop[j + 1].getValue()) {
                    Equipment temp = shop[j];
                    shop[j] = shop[j + 1];
                    shop[j + 1] = temp;
                }
            }
        }
        System.out.println("Done, inventory is now sorted by value!");
    }

    public static void sortByName() {
        //And another terrible sort, this time by selection :)
        //Doesn't sort first 6 array elements properly, i have no idea why.
        int min;
        for (int i = 0; i < shop.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < shop.length; j++) {
                if (shop[j].getName().compareTo(shop[min].getName()) < 0) {
                    min = j;
                }
                Equipment temp = shop[min];
                shop[min] = shop[i];
                shop[i] = temp;
            }
        }
        System.out.println("Done, inventory is now sorted by name!");
    }

    public static void searchInName(String input) {
        String searchParameter = input.toLowerCase();
        int searchResults = 0;
        for (int i = 0; i < shop.length; i++) {
            if (shop[i].getName().contains(searchParameter)) {
                searchResults++;
                System.out.print(searchResults + ". ");
                shop[i].print();
            }
        }
        if (searchResults == 0) {
            System.out.println("No results.");
        }
    }

    public static void searchByValueRange(float inputMin, float inputMax) {
        int searchResults = 0;
        if (inputMin < 0 || inputMax < 0){
            return;
        } else {
            if (inputMin > inputMax) {
                float temp = inputMin;
                inputMin = inputMax;
                inputMax = temp;
            }
            for (int i = 0; i < shop.length; i++) {
                if (Float.compare(shop[i].getValue(), inputMin) >= 0) {
                    if (Float.compare(shop[i].getValue(), inputMax) <= 0) {
                        searchResults++;
                        System.out.print(searchResults + ". ");
                        shop[i].print();
                    }
                }
            }
            if (searchResults == 0) {
                System.out.println("No results.");
            }
        }
    }
}
