package builder;

/**
 * @author Sergey Solovyov
 */
public class Main {

    private Main(){}

    public static void main(String[] args) {

        BoeingBuilder builder = new BoeingBuilder();
        PlaneBuilderDirector director = new PlaneBuilderDirector(builder);
        director.constructPlane();
        System.out.println(builder.getPlane().toString());
    }
}
