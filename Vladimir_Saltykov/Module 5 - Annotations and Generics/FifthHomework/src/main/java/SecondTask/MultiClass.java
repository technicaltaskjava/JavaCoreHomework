package SecondTask;


public class MultiClass <T> {

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    T value;



    public <T> T[] getArray(int size) {

        T[] arr = (T[])new Object[size];
        return arr;
    }

    }


