package com.epam.task1texttransformer;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Yuriy Krishtop on 22.03.2016.
 */
public class DeleteWordTest {

    @Test
    public void testDelLet() {
        TextTransformer myTextTr = new TextTransformer("file.txt");
        String str = "First, second, e";
        Assert.assertEquals("First, , e", myTextTr.deleteWords(str,6));
    }
}
