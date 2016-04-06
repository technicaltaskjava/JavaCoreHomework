package epam.com.task3;

/**
 * Created by O.Gondar on 05.04.2016.
 */
public class MyNumber<T extends Number> {

    private T value;

    public MyNumber(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
