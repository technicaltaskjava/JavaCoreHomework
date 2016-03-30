package javase.t03.tuples;

/**
 * Class Unit with one generic element
 * Created by Yury Vislobodsky on 27.03.2016.
 */
public class Unit<A> {
    private A a;

    Unit(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }

    @Override
    public String toString() {
        return a.toString();
    }
}
