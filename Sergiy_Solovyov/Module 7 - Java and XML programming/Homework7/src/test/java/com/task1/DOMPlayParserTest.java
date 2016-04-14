package com.task1;

import dom.DOMPlayParser;
import exceptions.MyDOMException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DOMPlayParserTest {

    @Test
    public void test() throws MyDOMException {
        DOMPlayParser playParser = new DOMPlayParser();
        playParser.parse("src/main/resources/test.xml");

        assertEquals(playParser.toString(), Constant.RESULT);
    }
}
