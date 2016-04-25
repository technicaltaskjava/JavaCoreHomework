package cuisine.japanese;

import cuisine.Cuisine;
import cuisine.FirstDishes;
import cuisine.SecondDishes;
import cuisine.Snacks;
import cuisine.japanese.first.SalmonSoup;
import cuisine.japanese.second.FriedNoodles;
import cuisine.japanese.snack.TigerPrawns;

/**
 * @author Alexey Ushakov
 */
public class JapaneseCuisine implements Cuisine {
    @Override
    public FirstDishes cookFirstDishes() {
        return new SalmonSoup();
    }

    @Override
    public SecondDishes cookSecondDishes() {
        return new FriedNoodles();
    }

    @Override
    public Snacks cookSnacks() {
        return new TigerPrawns();
    }
}
