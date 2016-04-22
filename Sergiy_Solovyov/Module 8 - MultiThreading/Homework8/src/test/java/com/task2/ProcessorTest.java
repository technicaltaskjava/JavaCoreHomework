package com.task2;

import com.task1.Constant;
import com.task2.application.Application;
import com.task2.parser.TextParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lammi on 17.04.2016.
 */
public class ProcessorTest {
    @Test
    public void runTest() throws InterruptedException {
        TextParser parser = new TextParser();
        Application processor = new Application(parser);
        processor.run();
        assertEquals(TextParser.accounts.toString(), Constant.ACCOUNTS);
    }
}
