package coffee.characteristics;

/**
 * @author Alexey Ushakov
 */
public enum CoffeeWeightJar {
    GRAM500, GRAM1000;

    public int getIntValue() {
        return Integer.valueOf(this.toString().substring(4));
    }
}
