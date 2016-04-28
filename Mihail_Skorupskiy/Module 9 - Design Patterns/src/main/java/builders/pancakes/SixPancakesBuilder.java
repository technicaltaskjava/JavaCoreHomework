package builders.pancakes;

import builders.FoodBuilder;
import foods.Pancakes;

public class SixPancakesBuilder extends AbstractPancakesBuilder implements FoodBuilder {

    public SixPancakesBuilder(){
        instance = new Pancakes("six");
    }

}
