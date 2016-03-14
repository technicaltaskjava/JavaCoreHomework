package extras;

public enum Materials {
    BRONZE, STEEL, SILVER, GOLDEN;

    public static float getModifier(Materials material) {
        return (material.ordinal() + 1)/2f;
    }
}
