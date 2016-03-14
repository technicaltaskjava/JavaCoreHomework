package management;

import equipment.pieces.*;

public class Knight {
    private static Sword mainHand;
    private static Armor body;
    private static Shield offHand;

    public static void setMainHand(String material, String weight){
        mainHand = new Sword(material, weight);
    }

    public static void setArmor(String material, String weight){
        body = new Armor(material, weight);
    }

    public static void setOffHand(String material, String weight){
        offHand = new Shield(material, weight);
    }

    public static void reset(){
        mainHand = null;
        body = null;
        offHand = null;
    }

    public static double getTotalValue(){
        double temp = 0;
        if (mainHand != null){
            temp += mainHand.getValue();
        }
        if (offHand != null){
            temp += offHand.getValue();
        }
        if (body != null) {
            temp += body.getValue();
        }
        return temp;
    }

    public static void printEquipment() {
        if (mainHand != null) {
            System.out.println("Main hand: " + mainHand.getName());
        } else {
            System.out.println("No sword equipped.");
        }
        if (body != null) {
            System.out.println("Body: " + body.getName());
        } else {
            System.out.println("No armor equipped.");
        }
        if (offHand != null) {
            System.out.println("Off hand: " + offHand.getName());
        } else {
            System.out.println("No shield equipped.");
        }
    }
}
