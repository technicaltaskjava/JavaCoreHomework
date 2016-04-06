package coffee.factory;

import coffee.CoffeePacking;
import coffee.JarGlass;
import coffee.JarTin;
import coffee.characteristics.CoffeeCondition;
import coffee.characteristics.CoffeeWeightJar;

/**
 * @author Alexey Ushakov
 */
public abstract class AbstractCoffeePackingJarFactory extends CoffeePackingFactory {

    public static CoffeeWeightJar getCustomWeight() {
        return CoffeeWeightJar.values()[random.nextInt(CoffeeWeightJar.values().length)];
    }

    public static CoffeeCondition getCustomCondition() {
        switch (random.nextInt(3)) {
            case 0:
                return CoffeeCondition.BEANS;
            case 1:
                return CoffeeCondition.GROUND;
            default:
                return CoffeeCondition.SOLUBLE;
        }
    }

    public static CoffeePacking getCoffeePacking() {
        if (random.nextInt(2) == 0) {
            return new JarGlass(getCustomCondition(), getCustomBrand(), getCustomWeight());
        } else {
            return new JarTin(getCustomCondition(), getCustomBrand(), getCustomWeight());
        }
    }


}
