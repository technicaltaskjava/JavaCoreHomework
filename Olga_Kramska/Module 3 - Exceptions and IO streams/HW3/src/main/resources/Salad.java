import com.epam.task2.ingredient.Vegetable;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Olga Kramska on 04-Mar-16.
 */
public class Salad implements ISalad {
    private Vegetable[] vegetables;

    public Salad(Vegetable[] vegetables) {
        this.vegetables = vegetables;
    }

    @Override
    public double calcCalories() {
        double totalCalories = 0;

        for (Vegetable ingredient : vegetables) {
            totalCalories += ingredient.getCalories();
        }
        return totalCalories;
    }

    @Override
    public Vegetable[] sortIngredients(Comparator<Vegetable> vegetableComparator) throws CloneNotSupportedException {
        Vegetable[] sortedSalad = vegetables.clone();
        Arrays.sort(sortedSalad, vegetableComparator);
        return sortedSalad;
    }

    @Override
    public Vegetable findIngredientWithMaxCalories() {
        Vegetable maxCalories = vegetables[0];
        for (int i = 1; i < vegetables.length; i++) {
            if (vegetables[i].getCalories() > maxCalories.getCalories()) {
                maxCalories = vegetables[i];
            }
        }
        return maxCalories;
    }

    @Override
    public String toString() {
        return "Salad{" + Arrays.toString(vegetables) + '}';
    }
}
