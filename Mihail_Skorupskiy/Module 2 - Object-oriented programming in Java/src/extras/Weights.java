package extras;

public enum Weights {
    LIGHT, MEDIUM, HEAVY;

    public static float getModifier(Weights type){
        return (type.ordinal() + 1) * 5;
    }
}
