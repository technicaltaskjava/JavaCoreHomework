package foods;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFood {

    private List<FoodElement> contents = new ArrayList<>();
    protected String type;

    protected AbstractFood(String type){
        this.type = type;
    }

    public void add(FoodElement element){
        contents.add(element);
    }

    protected void useContents(){
        for (FoodElement content : contents) {
            System.out.println(content);
        }
    }
}
