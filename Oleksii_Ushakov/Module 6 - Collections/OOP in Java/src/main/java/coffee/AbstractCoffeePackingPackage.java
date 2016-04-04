package coffee;

import coffee.characteristics.CoffeeWeightPackage;

/**
 * @author Alexey Ushakov
 */
abstract class AbstractCoffeePackingPackage extends AbstractCoffeePacking {
    protected CoffeeWeightPackage weightPackage;

    @Override
    public int getWeigh() {
        return weightPackage.getIntValue();
    }
}
