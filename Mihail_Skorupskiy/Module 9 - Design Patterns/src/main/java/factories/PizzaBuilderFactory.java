package factories;

import builders.FoodBuilder;
import builders.pizza.LargePizzaBuilder;
import builders.pizza.MediumPizzaBuilder;

public class PizzaBuilderFactory implements FoodBuilderFactory {

    public FoodBuilder getBuilder(String type){
        switch (type){
            case "large":
                return new LargePizzaBuilder();
            case "medium":
                return new MediumPizzaBuilder();
            default:
                System.out.println("No such type on the menu.");
                return null;
        }
    }
}
