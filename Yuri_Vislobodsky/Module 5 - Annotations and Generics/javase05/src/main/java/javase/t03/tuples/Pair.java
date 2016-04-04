package javase.t03.tuples;

/**
 * Class Pair with two generic elements
 * Created by Yury Vislobodsky on 27.03.2016.
 */
public class Pair<A,B> {
    private A a;
    private B b;

    Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    @Override
    public String toString() {
        return "(" + a + "," + b + ")";
    }
}
