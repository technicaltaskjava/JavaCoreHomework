package com.epam.builder.cocktailbilder;

import com.epam.builder.cocktail.Cocktail;

public abstract class CocktailBuilder {
    protected Cocktail cocktail;

    public Cocktail getCocktail() {
        return cocktail;
    }

    public void createCocktail() {
        cocktail = new Cocktail();
    }

    public abstract void buildGlass();
    public abstract void buildBasis();
    public abstract void buildModifier();
    public abstract void buildFiller();
    public abstract void buildIce();
    public abstract void buildTricks();

}
