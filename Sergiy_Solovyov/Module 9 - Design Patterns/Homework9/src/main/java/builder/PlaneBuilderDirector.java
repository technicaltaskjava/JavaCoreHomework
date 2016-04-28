package builder;

/**
 * @author Sergey Solovyov
 */
public class PlaneBuilderDirector {

    private PlaneBuilder planeBuilder;

    public PlaneBuilderDirector(PlaneBuilder planeBuilder) {
        this.planeBuilder = planeBuilder;
    }

    public void constructPlane() {
        planeBuilder.buildColor();
        planeBuilder.buildFuselage();
    }
}
