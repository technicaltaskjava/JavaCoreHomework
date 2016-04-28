package com.epam.builder;

/* Subject Area is Cocktail making. Every Cocktail contains of Glass, Basis, Modifier, Filler, Ice and Tricks. */

import com.epam.builder.cocktailbilder.JungleJuiceBuilder;

public class BuilderRun {
    private BuilderRun() {
    }

    public static void cocktailMakerExample() {
        CocktailMaker maker = new CocktailMaker();
        JungleJuiceBuilder builder = new JungleJuiceBuilder();

        maker.setCocktailMaker(builder);
        maker.makeCocktail();

        System.out.println(maker.getReadyCocktail().getGlass());
        System.out.println(maker.getReadyCocktail().getBasis());
        System.out.println(maker.getReadyCocktail().getModifier());
        System.out.println(maker.getReadyCocktail().getFiller());
        System.out.println(maker.getReadyCocktail().getIce());
        System.out.println(maker.getReadyCocktail().getTricks());
        System.out.println("Cocktail is ready!\n");
    }
}
