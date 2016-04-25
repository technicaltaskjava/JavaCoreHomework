package foods;

public class Pizza extends AbstractFood implements Food {

    public Pizza(String type){
        super(type);
    }

    public void serve(){
        System.out.println("Rolling the dough for your " + type + " pizza.");
        useContents();
        System.out.println("Baking... Done! Enjoy your pizza!");
    }
}
