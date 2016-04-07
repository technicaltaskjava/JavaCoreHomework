package knightgear.management;

import knightgear.equipment.pieces.*;
import knightgear.equipment.Equipment;
import knightgear.extras.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class EquipmentShop {

    private static int shopSize = (Materials.values().length) * (Weights.values().length) * 3;
    private static ArrayList<Equipment> shop = new ArrayList<>(shopSize);

    private EquipmentShop(){}

    public static void initialize() {
        for (int i = 0; i < shopSize / 3; i++) {
            shop.add(new Sword(Materials.values()[i % 4].toString(), Weights.values()[i / 4].toString()));
            shop.add(new Armor(Materials.values()[i % 4].toString(), Weights.values()[i / 4].toString()));
            shop.add(new Shield(Materials.values()[i % 4].toString(), Weights.values()[i / 4].toString()));
        }
    }

    public static void show() {
        for (int i = 0; i < shop.size(); i++) {
            System.out.print(i + 1 + ". ");
            shop.get(i).print();
        }
    }

    public static void sortByValue() {
        Collections.sort(shop, new ValueComparator());
        System.out.println("Done, inventory is now sorted by value!");
    }

    public static void sortByName() {
        Collections.sort(shop, new NameComparator());
        System.out.println("Done, inventory is now sorted by name!");
    }

    public static void searchInName(String input) {
        String searchParameter = input.toLowerCase();
        int searchResults = 0;
        for (int i = 0; i < shop.size(); i++) {
            if (shop.get(i).getName().contains(searchParameter)) {
                searchResults++;
                System.out.print(searchResults + ". ");
                shop.get(i).print();
            }
        }
        if (searchResults == 0) {
            System.out.println("No results.");
        }
    }

    public static void searchByValueRange(float inputMin, float inputMax) {
        int searchResults = 0;
        if (inputMin >= 0 && inputMax >= 0){
            float min;
            float max;
            if (inputMin > inputMax) {
                min = inputMax;
                max = inputMin;
            } else {
                min = inputMin;
                max = inputMax;
            }
            for (int i = 0; i < shop.size(); i++) {
                if (Float.compare(shop.get(i).getValue(), min) >= 0 && Float.compare(shop.get(i).getValue(), max) <= 0) {
                    searchResults++;
                    System.out.print(searchResults + ". ");
                    shop.get(i).print();
                }
            }
            if (searchResults == 0) {
                System.out.println("No results.");
            }
        }
    }
}

class ValueComparator implements Comparator<Equipment>{

    @Override
    public int compare(Equipment current, Equipment next){
        return Double.compare(current.getValue(), next.getValue());
    }
}

class NameComparator implements Comparator<Equipment>{

    @Override
    public int compare(Equipment current, Equipment next){
        return current.toString().compareTo(next.toString());
    }
}
