package abstractfactory.vehicle;

/**
 * Created by Lammi on 21.04.2016.
 */
public class Bus implements Vehicle {

    @Override
    public void move() {
        System.out.println("Buy ticket and move with another passengers");
    }
}
