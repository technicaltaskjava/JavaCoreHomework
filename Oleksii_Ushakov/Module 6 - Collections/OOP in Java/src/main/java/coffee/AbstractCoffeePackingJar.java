package coffee;

import coffee.characteristics.CoffeeWeightJar;

/**
 * @author Alexey Ushakov
 */
abstract class AbstractCoffeePackingJar extends AbstractCoffeePacking {
    protected CoffeeWeightJar weightJar;

    @Override
    public int getWeigh() {
        return weightJar.getIntValue();
    }
}
