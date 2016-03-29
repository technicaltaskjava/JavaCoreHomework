package Task3;

/**
 * Created by Oleg on 27.03.2016.
 */
public class Triplet<A, B, C> extends Pair<A, B> {

    public final C c;

    public Triplet(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    public C getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Triplet{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                "}";
    }
}
