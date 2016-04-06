package coffee;

import coffee.characteristics.CoffeeBrand;
import coffee.characteristics.CoffeeCondition;
import coffee.characteristics.CoffeeWeightPackage;

/**
 * @author Alexey Ushakov
 */
public class PackageVacuumSeam extends AbstractCoffeePackingPackage {
    private static final int DEFAULT_PRICE = 2500;

    public PackageVacuumSeam(int price, CoffeeCondition condition, CoffeeBrand brand, CoffeeWeightPackage weightPackage) {
        this.price = price;
        this.brand = brand;
        this.weightPackage = weightPackage;
        this.condition = condition;
    }

    public PackageVacuumSeam(CoffeeCondition condition, CoffeeBrand brand, CoffeeWeightPackage weightPackage) {
        this(0, condition, brand, weightPackage);
        this.price = DEFAULT_PRICE;
    }

    @Override
    public String getDescription() {
        return "Package with vacuum seam";
    }
}
