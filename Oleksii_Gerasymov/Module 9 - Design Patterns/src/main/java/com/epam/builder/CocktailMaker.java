package com.epam.builder;

import com.epam.builder.cocktail.Cocktail;
import com.epam.builder.cocktailbilder.CocktailBuilder;

public class CocktailMaker {
    private CocktailBuilder cocktailBuilder;

    public void setCocktailMaker(CocktailBuilder cocktailBuilder) {
        this.cocktailBuilder = cocktailBuilder;
    }

    public Cocktail getReadyCocktail() {
        return cocktailBuilder.getCocktail();
    }

    public void makeCocktail() {
        cocktailBuilder.createCocktail();
        cocktailBuilder.buildGlass();
        cocktailBuilder.buildBasis();
        cocktailBuilder.buildModifier();
        cocktailBuilder.buildFiller();
        cocktailBuilder.buildIce();
        cocktailBuilder.buildTricks();
    }
}
