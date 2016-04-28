package com.epam.builder.cocktail;

public class Cocktail {
    private String glass;
    private String basis;
    private String modifier;
    private String filler;
    private String ice;
    private String tricks;

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public void setTricks(String tricks) {
        this.tricks = tricks;
    }

    public String getGlass() {
        return glass;
    }

    public String getBasis() {
        return basis;
    }

    public String getModifier() {
        return modifier;
    }

    public String getFiller() {
        return filler;
    }

    public String getIce() {
        return ice;
    }

    public String getTricks() {
        return tricks;
    }
}
