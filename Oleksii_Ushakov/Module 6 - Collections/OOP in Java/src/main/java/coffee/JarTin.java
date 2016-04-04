package coffee;

import coffee.characteristics.CoffeeBrand;
import coffee.characteristics.CoffeeCondition;
import coffee.characteristics.CoffeeWeightJar;

/**
 * @author Alexey Ushakov
 */
public class JarTin extends AbstractCoffeePackingJar {
    private static final int DEFAULT_PRICE = 5000;

    public JarTin(int price, CoffeeCondition condition, CoffeeBrand brand, CoffeeWeightJar weightJar) {
        this.price = price;
        this.brand = brand;
        this.weightJar = weightJar;
        this.condition = condition;
    }

    public JarTin(CoffeeCondition condition, CoffeeBrand brand, CoffeeWeightJar weightJar) {
        this(0, condition, brand, weightJar);
        this.price = DEFAULT_PRICE;
    }

    @Override
    public String getDescription() {
        return "Tin jar";
    }
}