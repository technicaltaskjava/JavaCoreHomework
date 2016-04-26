package abstractfactory.plane;

/**
 * @author Sergey Solovyov
 */
public class Boeing implements Plane {
    @Override
    public void fly() {
        System.out.println("I'm fly to move passengers");
    }
}
