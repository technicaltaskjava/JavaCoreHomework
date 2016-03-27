package com.epam.task2logger;

import com.epam.task2logger.CrazyLogger;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Администратор on 22.03.2016.
 */
public class CreazyLoggerTest {

    @Test
    public void testCrazyLogger() {
        CrazyLogger myLogger = new CrazyLogger("logtest.txt");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy : hh-mm");
        String date = sdf.format(new Date());
        String message = "Test message.";
        String sentence = " " + date + " \u2014 " + message + " " + "\n";
        myLogger.addLog("Hello!");
        myLogger.addLog(message);
        myLogger.addLog("Some text");
        Assert.assertEquals(sentence, myLogger.findLog("mes"));
    }
}
