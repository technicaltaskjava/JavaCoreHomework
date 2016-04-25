package foods;

public class IceCream extends AbstractFood implements Food{

    public IceCream(String type){
        super(type);
    }

    public void serve(){
        System.out.println("Taking a " + type + "-shaped wafer for your ice cream.");
        useContents();
        System.out.println("Enjoy your ice cream!");
    }
}
