package builders.icecream;

import builders.FoodBuilder;
import foods.IceCream;

public class CupIceCreamBuilder extends AbstractIceCreamBuilder implements FoodBuilder {

    public CupIceCreamBuilder(){
        instance = new IceCream("cup");
    }

}
