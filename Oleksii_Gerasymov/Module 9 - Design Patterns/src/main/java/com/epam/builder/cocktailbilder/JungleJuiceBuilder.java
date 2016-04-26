package com.epam.builder.cocktailbilder;

public class JungleJuiceBuilder extends CocktailBuilder {
    @Override
    public void buildGlass() {
        cocktail.setGlass("Large Glass");
    }

    @Override
    public void buildBasis() {
        cocktail.setBasis("Tequila");
    }

    @Override
    public void buildModifier() {
        cocktail.setModifier("Pisang");
    }

    @Override
    public void buildFiller() {
        cocktail.setFiller("Orange Juice");
    }

    @Override
    public void buildIce() {
        cocktail.setIce("Three ice cubes");
    }

    @Override
    public void buildTricks() {
        cocktail.setTricks("No Tricks");
    }
}
