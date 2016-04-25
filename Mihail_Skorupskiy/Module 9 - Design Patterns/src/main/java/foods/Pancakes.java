package foods;

public class Pancakes extends AbstractFood implements Food {

    public Pancakes(String type){
        super(type);
    }

    public void serve(){
        System.out.println("Stacking " + type + " pancakes for your serving.");
        useContents();
        System.out.println("Enjoy your pancakes!");
    }
}
