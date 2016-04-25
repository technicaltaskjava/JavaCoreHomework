package builders;

import foods.Food;

public abstract class AbstractFoodBuilder implements FoodBuilder {

    protected Food instance;

    //In order to be a proper builder, this method needs to return FoodBuilder instead of void.
    //But that's pointless in context of this program, and if i do it anyway, and have something
    //like "foodBuilder = foodBuilder.addContent("...");", Sonar will have something to say about it.
    public abstract void addContent(String content);

    public Food getFood(){
        return instance;
    }
}
