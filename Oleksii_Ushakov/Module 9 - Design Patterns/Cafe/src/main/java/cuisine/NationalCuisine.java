package cuisine;

import cuisine.japanese.JapaneseCuisine;
import cuisine.mexican.MexicanCuisine;

/**
 * @author Alexey Ushakov
 */
public class NationalCuisine {
    private Cuisine cuisine;

    public NationalCuisine(Nation nation) {
        if (nation == Nation.JAPANESE) {
            cuisine = new JapaneseCuisine();
        } else {
            cuisine = new MexicanCuisine();
        }
    }

    public FirstDishes cookFirstDishes() {
        return cuisine.cookFirstDishes();
    }

    public SecondDishes cookSecondDishes() {
        return cuisine.cookSecondDishes();
    }

    public Snacks cookSnacks() {
        return cuisine.cookSnacks();
    }
}
