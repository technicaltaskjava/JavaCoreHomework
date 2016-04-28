package builders.icecream;


import builders.AbstractFoodBuilder;
import foods.flavors.*;

public abstract class AbstractIceCreamBuilder extends AbstractFoodBuilder {

    public void addContent(String content){
        switch (content) {
            case "":
                break;
            case "chocolate":
                instance.add(new Chocolate());
                break;
            case "strawberry":
                instance.add(new Strawberry());
                break;
            case "vanilla":
                instance.add(new Vanilla());
                break;
            default:
                System.out.println("No such item on the menu.");
        }
    }
}
