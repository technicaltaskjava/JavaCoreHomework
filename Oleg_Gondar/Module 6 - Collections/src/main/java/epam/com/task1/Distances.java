package epam.com.task1;


/**
 * Created by O.Gondar on 05.04.2016.
 */
public enum Distances {

    LONG(3), MIDDLE(2), SHORT(1);

    private final int distValue;

    Distances(int distValue) {
        this.distValue = distValue;
    }

    public int getDistValue() {
        return distValue;
    }
}
