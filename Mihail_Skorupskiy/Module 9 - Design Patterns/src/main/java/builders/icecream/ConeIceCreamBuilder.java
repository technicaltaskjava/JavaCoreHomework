package builders.icecream;

import builders.FoodBuilder;
import foods.IceCream;

public class ConeIceCreamBuilder extends AbstractIceCreamBuilder implements FoodBuilder {

    public ConeIceCreamBuilder(){
        instance = new IceCream("cone");
    }
}
