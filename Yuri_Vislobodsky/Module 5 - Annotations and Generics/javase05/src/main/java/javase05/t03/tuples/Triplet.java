package javase05.t03.tuples;

/**
 * Class Triplet with three generic elements
 * Created by Yury Vislobodsky on 27.03.2016.
 */
public class Triplet<A,B,C> {
    private A a;
    private B b;
    private C c;

    Triplet(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public C getC() {
        return c;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("(").append(a).append(",").
                append(b).append(",").append(c).append(")").toString();
    }
}

