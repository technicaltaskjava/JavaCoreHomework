package builders.pizza;

import builders.AbstractFoodBuilder;
import foods.toppings.*;

public abstract class AbstractPizzaBuilder extends AbstractFoodBuilder {

    public void addContent(String content){
        switch (content) {
            case "":
                break;
            case "capers":
                instance.add(new Capers());
                break;
            case "mushrooms":
                instance.add(new Mushrooms());
                break;
            case "pepperoni":
                instance.add(new Pepperoni());
                break;
            case "olives":
                instance.add(new Olives());
                break;
            default:
                System.out.println("No such item on the menu.");
        }
    }
}
