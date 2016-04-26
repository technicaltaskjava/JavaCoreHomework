package abstractfactory.plane;

/**
 * @author Sergey Solovyov
 */
public class CargoPlane implements Plane {
    @Override
    public void fly() {
        System.out.println("I'm flying to carry cargo");
    }
}
