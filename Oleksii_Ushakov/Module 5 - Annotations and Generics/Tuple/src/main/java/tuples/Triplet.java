package tuples;

/**
 * @author Alexey Ushakov
 */
public class Triplet<A, B, C> {
    private A firstValue;
    private B secondValue;
    private C thirdValue;

    private Triplet(A firstValue, B secondValue, C thirdValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }

    public A getFirstValue() {
        return firstValue;
    }

    public B getSecondValue() {
        return secondValue;
    }

    public C getThirdValue() {
        return thirdValue;
    }

    public static <A, B, C> Triplet<A, B, C> create(A firstValue, B secondValue, C thirdValue) {
        return new Triplet<>(firstValue, secondValue, thirdValue);
    }

}
