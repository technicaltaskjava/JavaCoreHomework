package builder;

/**
 * @author Sergey Solovyov
 */
public class BoeingBuilder implements PlaneBuilder {

    private Plane plane;

    public BoeingBuilder() {
        this.plane = new Plane("Boeing");
    }

    @Override
    public void buildColor() {
        plane.setColor(Plane.Color.BLACK);
    }

    @Override
    public void buildFuselage() {
        plane.setFuselage(Plane.Fuselage.NACELLE);
    }

    public Plane getPlane() {
        return plane;
    }
}
