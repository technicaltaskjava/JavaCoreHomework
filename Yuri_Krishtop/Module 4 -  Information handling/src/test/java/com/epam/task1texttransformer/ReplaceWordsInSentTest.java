package com.epam.task1texttransformer;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Yuriy Krishtop on 22.03.2016.
 */
public class ReplaceWordsInSentTest {

    @Test
    public void testReplaceWordsInSentns() {
        TextTransformer myTextTrans = new TextTransformer("file.txt");
        String[] str = {"First second third"};
        Assert.assertEquals("third second First", myTextTrans.replaceWordsInSentens(str));
    }
}
