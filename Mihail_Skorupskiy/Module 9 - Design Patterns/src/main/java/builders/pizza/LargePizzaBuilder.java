package builders.pizza;

import builders.FoodBuilder;
import foods.Pizza;

public class LargePizzaBuilder extends AbstractPizzaBuilder implements FoodBuilder {

    public LargePizzaBuilder(){
        instance = new Pizza("large");
    }
}
