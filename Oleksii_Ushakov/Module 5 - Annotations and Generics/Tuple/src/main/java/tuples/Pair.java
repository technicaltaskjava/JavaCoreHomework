package tuples;

/**
 * @author Alexey Ushakov
 */
public class Pair<A, B> {
    private A firstValue;
    private B secondValue;

    private Pair(A firstValue, B secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public A getFirstValue() {
        return firstValue;
    }

    public B getSecondValue() {
        return secondValue;
    }

    public static <A, B> Pair<A, B> create(A firstValue, B secondValue) {
        return new Pair<>(firstValue, secondValue);
    }
}
