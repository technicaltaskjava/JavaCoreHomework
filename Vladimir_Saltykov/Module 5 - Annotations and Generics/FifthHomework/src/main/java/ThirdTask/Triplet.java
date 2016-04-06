package ThirdTask;

public class Triplet< T,T2,T3 > {


    T name;
    T2 value;
    T3 hashCode;


    public Triplet(T t, T2 t2, T3 t3 ) {
        setName(t);
        setValue(t2);
        setHashCode(t3);
    }

    public T3 getHashCode() {
        return hashCode;
    }

    public void setHashCode(T3 hashCode) {
        this.hashCode = hashCode;
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



    public String toString() {
        if (name == null) return null;
        return name.getClass().getName() + " " + name+ "; " + value.getClass().getName() + " " + value + "; " + hashCode.getClass().getName() + " " + hashCode;

    }
}
