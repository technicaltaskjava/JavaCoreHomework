package task3;

import java.io.Serializable;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 23.03.2016
 */
public class Pair <A, B>  {

    private final A first;
    private final B second;

    private Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair <A, B> createPair(A first, B second){
        return new Pair<>(first, second);
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
        Pair<A, B> other = (Pair<A, B>) o;

        return other.first == this.first && other.second == this.second;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((first == null) ? 0 : first.hashCode());
        result = 31 * result + ((second == null) ? 0 : second.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "{" + first +", "+ second + "}";
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

}
