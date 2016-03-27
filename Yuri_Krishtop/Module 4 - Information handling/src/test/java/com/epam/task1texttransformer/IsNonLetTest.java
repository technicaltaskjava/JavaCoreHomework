package com.epam.task1texttransformer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yuriy Krishtop on 21.03.2016.
 */
public class IsNonLetTest {


    @Test
    public void testIsNonLet() throws Exception {
        String character = "(";
        String  character2 = "h";
        Assert.assertTrue(TextTransformer.isNonLet(character));
        Assert.assertFalse(TextTransformer.isNonLet(character2));
    }

}
