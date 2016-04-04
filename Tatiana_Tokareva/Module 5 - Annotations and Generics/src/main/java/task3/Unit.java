package task3;

public class Unit<A> {
    private A value;

    private Unit(A value) {
        this.value = value;
    }

    public static <A> Unit create(A value) {
        return new Unit<>(value);
    }

    public A getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "value=" + value +
                '}';
    }
}
