package factories;

import builders.FoodBuilder;
import builders.pancakes.EightPancakesBuilder;
import builders.pancakes.SixPancakesBuilder;

public class PancakesBuilderFactory implements FoodBuilderFactory {

    public FoodBuilder getBuilder(String type){
        switch (type){
            case "six":
                return new SixPancakesBuilder();
            case "eight":
                return new EightPancakesBuilder();
            default:
                System.out.println("No such type on the menu.");
                return null;
        }
    }
}
