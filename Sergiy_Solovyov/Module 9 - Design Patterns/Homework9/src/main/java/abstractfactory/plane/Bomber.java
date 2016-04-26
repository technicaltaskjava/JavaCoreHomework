package abstractfactory.plane;

/**
 * @author Sergey Solovyov
 */
public class Bomber implements Plane {

    @Override
    public void fly() {
        System.out.println("I can fly very fast, I fly to make bad things");
    }
}
