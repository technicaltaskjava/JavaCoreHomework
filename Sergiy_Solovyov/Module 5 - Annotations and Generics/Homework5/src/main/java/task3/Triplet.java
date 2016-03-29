package task3;

import java.io.Serializable;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 23.03.2016
 */
public class Triplet <A, B, C> implements Serializable{

    private final A first;
    private final B second;
    private final C third;

    private Triplet(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    public static <A, B, C> Triplet <A, B, C> createTriplet(A first, B second, C third ){
        return new Triplet<>(first , second, third);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Pair)){
            return false;
        }
        Triplet<A, B, C> other = (Triplet<A, B, C>) o;
        return other.first == this.first && other.second == this.second && other.third == this.third;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((first == null) ? 0 : first.hashCode());
        result = 31 * result + ((second == null) ? 0 : second.hashCode());
        result = 31 * result + ((third == null) ? 0 : third.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "{" + first +", " + second +", "+ third + "}";
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }
}
