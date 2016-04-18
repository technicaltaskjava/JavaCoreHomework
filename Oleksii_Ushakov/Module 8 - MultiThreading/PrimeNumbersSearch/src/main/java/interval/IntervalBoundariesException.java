package interval;

/**
 * @author Alexey Ushakov
 */
public class IntervalBoundariesException extends IllegalArgumentException {
    public IntervalBoundariesException(String s) {
        super(s);
    }

    public IntervalBoundariesException(String message, Throwable cause) {
        super(message, cause);
    }
}
