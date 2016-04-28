package extras;

public class Menu {
    //This is supposed to be an example of Singleton pattern,
    //but i couldn't think of anything to make use of it's advantages.
    //Right now the menu could just as well be one static method show().
    private static Menu instance = new Menu();

    private Menu() {}

    public static Menu getInstance() {
        return instance;
    }

    public void show(){
        System.out.println("Pizzas:\n\t\tLarge\n\t\tMedium");
        System.out.println("\tAvailable toppings:");
        for (Toppings topping : Toppings.values()) {
            System.out.println("\t\t" + topping.name().toLowerCase());
        }
        System.out.println("Ice creams:\n\t\tCone\n\t\tCup");
        System.out.println("\tAvailable flavors:");
        for (Flavors flavor : Flavors.values()) {
            System.out.println("\t\t" + flavor.name().toLowerCase());
        }
        System.out.println("Pancakes:\n\t\tPortion of six\n\t\tPortion of eight");
        System.out.println("\tAvailable syrups:");
        for (Syrups syrup : Syrups.values()) {
            System.out.println("\t\t" + syrup.name().toLowerCase());
        }

    }

}
