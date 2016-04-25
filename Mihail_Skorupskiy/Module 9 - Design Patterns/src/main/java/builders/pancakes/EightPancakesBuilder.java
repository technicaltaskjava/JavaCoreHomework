package builders.pancakes;

import builders.FoodBuilder;
import foods.Pancakes;

public class EightPancakesBuilder extends AbstractPancakesBuilder implements FoodBuilder {

    public EightPancakesBuilder(){
        instance = new Pancakes("eight");
    }

}
