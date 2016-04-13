package com.task1;

import org.junit.Test;
import stax.StAXParser;
import javax.xml.stream.XMLStreamException;

import static org.junit.Assert.assertEquals;


public class StAXParserTest {

    @Test
    public void test() throws XMLStreamException {

        StAXParser stAX = new StAXParser();
        stAX.parse("src/main/resources/test.xml");
        assertEquals(stAX.toString(), Constant.RESULT);
    }
}
