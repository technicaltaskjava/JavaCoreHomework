package com.epam.task1texttransformer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yuriy Krishtop on 21.03.2016.
 */
public class IsConsonantTest {


    @Test
    public void testIsConsonant() throws Exception {
        char character = 'g';
        char character2 = 'у';
        Assert.assertTrue(TextTransformer.isConsonant(character));
        Assert.assertFalse(TextTransformer.isConsonant(character2));
    }

}
