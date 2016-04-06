package coffee.characteristics;

/**
 * @author Alexey Ushakov
 */
public enum CoffeeWeightPackage {
    GRAM50, GRAM100, GRAM150, GRAM200, GRAM250, GRAM500;

    public int getIntValue() {
        return Integer.valueOf(this.toString().substring(4));
    }
}
