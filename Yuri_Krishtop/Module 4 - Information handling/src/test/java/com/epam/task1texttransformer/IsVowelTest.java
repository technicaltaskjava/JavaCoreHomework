package com.epam.task1texttransformer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yuriy Krishtop on 21.03.2016.
 */
public class IsVowelTest {


    @Test
    public void testIsVowel() throws Exception {
        char character = 'и';
        char character2 = 'k';
        Assert.assertTrue(TextTransformer.isVowel(character));
        Assert.assertFalse(TextTransformer.isVowel(character2));
    }

}
