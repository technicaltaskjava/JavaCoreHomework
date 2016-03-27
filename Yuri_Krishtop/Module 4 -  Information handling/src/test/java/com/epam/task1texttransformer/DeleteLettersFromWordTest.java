package com.epam.task1texttransformer;

import org.junit.Assert;
import org.junit.Test;


import static com.epam.task1texttransformer.TextTransformer.deleteLettersFromWord;

/**
 * Created by Yuriy Krishtop on 22.03.2016.
 */
public class DeleteLettersFromWordTest {

    @Test
    public void testDelLet() {
        String str = "First, secosnd";
        Assert.assertEquals("First, second", deleteLettersFromWord(str));
    }
}
