package factories;

import builders.FoodBuilder;
import builders.icecream.ConeIceCreamBuilder;
import builders.icecream.CupIceCreamBuilder;

public class IceCreamBuilderFactory implements FoodBuilderFactory {

    public FoodBuilder getBuilder(String type){
        switch (type){
            case "cup":
                return new CupIceCreamBuilder();
            case "cone":
                return new ConeIceCreamBuilder();
            default:
                System.out.println("No such type on the menu.");
                return null;
        }
    }
}
