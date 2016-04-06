package coffee;

import coffee.characteristics.CoffeeBrand;
import coffee.characteristics.CoffeeCondition;

/**
 * @author Alexey Ushakov
 */
public interface CoffeePacking extends Comparable<CoffeePacking> {
    CoffeeBrand getBrand();

    CoffeeCondition getCondition();

    int getWeigh();

    int getPrice();

    void setPrice(int price);

    String getDescription();
}
