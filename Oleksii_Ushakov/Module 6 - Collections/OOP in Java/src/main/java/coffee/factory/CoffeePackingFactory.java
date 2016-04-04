package coffee.factory;

import coffee.CoffeePacking;
import coffee.characteristics.CoffeeBrand;
import coffee.characteristics.CoffeePackingType;

import java.util.Random;

/**
 * @author Alexey Ushakov
 */
public class CoffeePackingFactory {//NOSONAR
    protected static Random random = new Random();

    public static CoffeePackingType getCustomType() {
        return CoffeePackingType.values()[random.nextInt(CoffeePackingType.values().length)];
    }

    protected static CoffeeBrand getCustomBrand() {
        return CoffeeBrand.values()[random.nextInt(CoffeeBrand.values().length)];
    }

    public static CoffeePacking getCustomCoffeePacking(CoffeePackingType type) {
        if (type == CoffeePackingType.JAR) {
            return AbstractCoffeePackingJarFactory.getCoffeePacking();
        } else {
            return AbstractCoffeePackingPackageFactory.getCoffeePacking();
        }
    }
}
