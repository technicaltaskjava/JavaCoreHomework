package cuisine.mexican;

import cuisine.Cuisine;
import cuisine.FirstDishes;
import cuisine.SecondDishes;
import cuisine.Snacks;
import cuisine.mexican.first.ChillyBeanSoup;
import cuisine.mexican.second.Burrito;
import cuisine.mexican.snack.Quesadilla;

/**
 * @author Alexey Ushakov
 */
public class MexicanCuisine implements Cuisine {
    @Override
    public FirstDishes cookFirstDishes() {
        return new ChillyBeanSoup();
    }

    @Override
    public SecondDishes cookSecondDishes() {
        return new Burrito();
    }

    @Override
    public Snacks cookSnacks() {
        return new Quesadilla();
    }
}
