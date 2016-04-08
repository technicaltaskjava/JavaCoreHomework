package ThirdTask;


public class Pair <T, T2> {

    public Pair(T t, T2 t2) {
        setName(t);
        setValue(t2);
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public T2 getValue() {
        return value;
    }

    public void setValue(T2 value) {
        this.value = value;
    }

    T name;
    T2 value;

    public String toString() {
        if (name == null) return null;
        return name.getClass().getName() + " " + name + "; " + value.getClass().getName() + " " + value;

    }

}
