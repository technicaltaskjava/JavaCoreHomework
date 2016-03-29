package task3;

import java.io.Serializable;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 23.03.2016
 */
public class Unit <A> implements Serializable {

    private final A first;

    private Unit(A a){
        first = a;
    }

    public static <A> Unit <A> createUnit(A first){
        return new Unit<>(first);

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Unit)){
            return false;
        }
        Unit<A> other = (Unit<A>) o;
        return other.first == this.first;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((first == null) ? 0 : first.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "{" + first + "}";
    }

    public A getFirst() {
        return first;
    }

}
