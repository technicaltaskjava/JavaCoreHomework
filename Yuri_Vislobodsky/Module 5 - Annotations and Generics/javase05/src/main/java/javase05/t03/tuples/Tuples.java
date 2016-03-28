package javase05.t03.tuples;

/**
 * Class Tuples for handling with Unit, Pair and Triplet elements
 * Created by Yury Vislobodsky on 27.03.2016.
 */
public class Tuples {
    public static <A> Unit<A> create(A a) {
        return new Unit<A>(a);
    }

    public static <A, B> Pair<A, B> create(A a, B b) {
        return new Pair<A, B>(a, b);
    }

    public static <A, B, C> Triplet<A, B, C> create(A a, B b, C c) {
        return new Triplet<A, B, C>(a, b, c);
    }
}
