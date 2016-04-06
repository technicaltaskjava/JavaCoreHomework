package ThirdTask;


public class Unit <T> {


    public Unit(T t) {
        setName(t);
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    T name;

    public String toString() {
        if (name == null) return null;
        return name.getClass().getName() + " " + name;

    }
}
