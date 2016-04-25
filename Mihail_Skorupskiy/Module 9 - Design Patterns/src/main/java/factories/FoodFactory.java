package factories;

public class FoodFactory {

    private FoodFactory(){}

    public static FoodBuilderFactory getFactory(String type){
        switch (type){
            case "pizza":
                return new PizzaBuilderFactory();
            case "ice cream":
                return new IceCreamBuilderFactory();
            case "pancakes":
                return new PancakesBuilderFactory();
            default:
                System.out.println("No such item on the menu.");
                return null;
        }
    }
}
