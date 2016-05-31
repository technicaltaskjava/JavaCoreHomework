package dao.util;

/**
 * @author augustprime
 */
public class Predication {
    private final int id;
    private final String value;

    public Predication(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
