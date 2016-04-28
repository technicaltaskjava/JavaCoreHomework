package com.epam.builder;

import com.epam.builder.cocktailbilder.MoxitoBuilder;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BuilderTest {
    @Test
    public void builderTest() {
        CocktailMaker maker = new CocktailMaker();
        MoxitoBuilder builder = new MoxitoBuilder();

        maker.setCocktailMaker(builder);
        maker.makeCocktail();

        assertEquals("Large Glass", maker.getReadyCocktail().getGlass());
        assertEquals("Rum", maker.getReadyCocktail().getBasis());
        assertEquals("No Modifier", maker.getReadyCocktail().getModifier());
        assertEquals("Tonic", maker.getReadyCocktail().getFiller());
        assertEquals("Crushed Ice", maker.getReadyCocktail().getIce());
        assertEquals("Lime, Mint And Cane Sugar", maker.getReadyCocktail().getTricks());
    }
}
