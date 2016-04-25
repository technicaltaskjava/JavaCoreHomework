package builders.pizza;

import builders.FoodBuilder;
import foods.Pizza;

public class MediumPizzaBuilder extends AbstractPizzaBuilder implements FoodBuilder {

    public MediumPizzaBuilder(){
        instance = new Pizza("medium");
    }
}
