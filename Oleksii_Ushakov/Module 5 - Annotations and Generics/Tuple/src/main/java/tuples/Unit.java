package tuples;

/**
 * @author Alexey Ushakov
 */
public class Unit<A> {
    private A value;

    private Unit(A value) {
        this.value = value;
    }

    public A getValue() {
        return value;
    }

    public static <A> Unit<A> create(A value) {
        return new Unit<>(value);
    }
}
