package com.epam.builder.cocktailbilder;

public class MoxitoBuilder extends CocktailBuilder {
    @Override
    public void buildGlass() {
        cocktail.setGlass("Large Glass");
    }

    @Override
    public void buildBasis() {
        cocktail.setBasis("Rum");
    }

    @Override
    public void buildModifier() {
        cocktail.setModifier("No Modifier");
    }

    @Override
    public void buildFiller() {
        cocktail.setFiller("Tonic");
    }

    @Override
    public void buildIce() {
        cocktail.setIce("Crushed Ice");
    }

    @Override
    public void buildTricks() {
        cocktail.setTricks("Lime, Mint And Cane Sugar");
    }
}
