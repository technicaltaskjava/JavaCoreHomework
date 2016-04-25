package builders.pancakes;

import builders.AbstractFoodBuilder;
import foods.syrups.*;

public abstract class AbstractPancakesBuilder extends AbstractFoodBuilder {

    public void addContent(String content){
        switch (content){
            case "":
                break;
            case "gingerbread":
                instance.add(new Gingerbread());
                break;
            case "honey":
                instance.add(new Honey());
                break;
            case "maple":
                instance.add(new Maple());
                break;
            default:
                System.out.println("No such item on the menu.");
        }
    }
}
