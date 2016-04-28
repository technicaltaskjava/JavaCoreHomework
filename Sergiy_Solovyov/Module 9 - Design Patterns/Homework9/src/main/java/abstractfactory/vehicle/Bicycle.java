package abstractfactory.vehicle;

/**
 * Created by Lammi on 21.04.2016.
 */
public class Bicycle implements Vehicle {

    @Override
    public void move() {
        System.out.println("You should push bike pedals to start moving");
    }
}
