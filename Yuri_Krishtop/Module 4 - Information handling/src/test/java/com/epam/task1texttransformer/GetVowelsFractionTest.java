package com.epam.task1texttransformer;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yuriy Krishtop on 22.03.2016.
 */
public class GetVowelsFractionTest {

    @Test
    public void testDelLet() {
        WordComparer myWordComp1 = new WordComparer("seco");
        WordComparer myWordComp2 = new WordComparer("И");
        WordComparer myWordComp3 = new WordComparer("ф");
        String str1 = "seco";
        String str2 = "И";
        String str3 = "ф";
        Assert.assertTrue(0.5 == myWordComp1.getVowelsFraction());
        Assert.assertTrue(1.0 == myWordComp2.getVowelsFraction());
        Assert.assertTrue(0.0 == myWordComp3.getVowelsFraction());
    }


}
