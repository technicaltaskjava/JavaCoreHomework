package Task3;

/**
 * Created by Oleg on 27.03.2016.
 */
public class Unit<T> {

    private T a;

    public Unit(T a) {
        this.a = a;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "a=" + a +
                '}';
    }
}
