package com.epam.task3regexp;

import org.junit.Assert;
import org.junit.Test;



/**
 * Created by Администратор on 22.03.2016.
 */
public class GetSentencesTest {

    @Test
    public void getSentenceTest() {
        FindSentenceWithImageLink myFind = new FindSentenceWithImageLink("filename.txt");
        String str = "First sentence with figure1. Second sentence.";
        Assert.assertEquals("First sentence with Рис. 1", myFind.getSentences(str)[0]);
    }
}
