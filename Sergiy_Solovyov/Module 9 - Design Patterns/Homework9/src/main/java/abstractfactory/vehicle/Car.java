package abstractfactory.vehicle;

/**
 * Created by Lammi on 21.04.2016.
 */
public class Car implements Vehicle {
    @Override
    public void move() {
        System.out.println("Comfortable driving");
    }
}
