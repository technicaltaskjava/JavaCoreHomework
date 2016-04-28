package logic;

import builders.FoodBuilder;
import extras.Menu;
import factories.FoodBuilderFactory;
import factories.FoodFactory;
import foods.Food;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//All abstract classes in this project are not necessary,
//they just contain common implementations and elements.
//Without them there would be a lot of copy-pasted code.

public class FoodShop {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private FoodShop(){}

    public static void main(String[] args) {
        System.out.println("Welcome to the shop!");
        int input;
        do {
            showMainMenu();
            input = intInput();
            switch (input){
                case 0:
                    break;
                case 1:
                    Menu.getInstance().show();
                    break;
                case 2:
                    makeFood();
                    break;
                default:
            }
        } while (input != 0);
    }

    static int intInput(){
        try{
            return Integer.parseInt(validatedInput());
        } catch (NumberFormatException e){
            System.out.println("Input is not a number. " + e);
            return -1;
        }
    }

    static String validatedInput(){
        try {
            return reader.readLine().toLowerCase();
        } catch (IOException e){
            System.out.println("Input error. " + e);
            return "";
        }
    }

    static void showMainMenu(){
        System.out.println("\nWhat would you like to do?");
        System.out.println("1 - Browse menu.");
        System.out.println("2 - Make an order.");
        System.out.println("0 - Leave.");
    }

    //Methods below could be made into one, but it would become a little bit too big for my liking :)
    private static void makeFood(){
        System.out.println("What food would you like?");
        String textInput = validatedInput();
        FoodBuilderFactory fbf = FoodFactory.getFactory(textInput);
        if (fbf != null) {
            makeFoodBuilder(fbf, textInput);
        }
    }

    private static void makeFoodBuilder(FoodBuilderFactory fbf, String input){
        System.out.println("What type of " + input + " would you like?");
        String textInput = validatedInput();
        FoodBuilder fb = fbf.getBuilder(textInput);
        if (fb != null) {
            fillFood(fb);
        }
    }

    private static void fillFood(FoodBuilder fb){
        System.out.println("Add as many food elements as you like:");
        System.out.println("(leave the input blank to get to cooking)");
        String input;
        do {
            input = validatedInput();
            fb.addContent(input);
        } while (!"".equals(input));
        Food food = fb.getFood();
        food.serve();
    }

}
