package coffee.factory;

import coffee.CoffeePacking;
import coffee.PackageCentralSeam;
import coffee.PackageVacuumSeam;
import coffee.characteristics.CoffeeCondition;
import coffee.characteristics.CoffeeWeightPackage;

/**
 * @author Alexey Ushakov
 */
public abstract class AbstractCoffeePackingPackageFactory extends CoffeePackingFactory {

    private static CoffeeWeightPackage getCustomWeight() {
        return CoffeeWeightPackage.values()[random.nextInt(CoffeeWeightPackage.values().length)];
    }

    private static CoffeeCondition getCustomCondition() {
        if (random.nextInt(2) == 0) {
            return CoffeeCondition.CAPSULES;
        } else {
            return CoffeeCondition.SOLUBLE;
        }
    }

    public static CoffeePacking getCoffeePacking() {
        if (random.nextInt(2) == 0) {
            return new PackageCentralSeam(getCustomCondition(), getCustomBrand(), getCustomWeight());
        } else {
            return new PackageVacuumSeam(getCustomCondition(), getCustomBrand(), getCustomWeight());
        }
    }


}
