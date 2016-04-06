package coffee;

import coffee.characteristics.CoffeeBrand;
import coffee.characteristics.CoffeeCondition;

/**
 * @author Alexey Ushakov
 */

abstract class AbstractCoffeePacking implements CoffeePacking {
    protected CoffeeBrand brand;
    protected CoffeeCondition condition;
    protected int price;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    @Override
    public CoffeeBrand getBrand() {
        return brand;
    }

    @Override
    public CoffeeCondition getCondition() {
        return condition;
    }

    @Override
    public int compareTo(CoffeePacking packing) {
        return this.price - packing.getPrice();
    }
}
