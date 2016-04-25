package builders;

import foods.Food;

public interface FoodBuilder {
    Food getFood();
    void addContent(String content);
}
