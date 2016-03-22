package task2;

import org.junit.Test;
import task3.HTMLParser;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 21.03.2016
 */
public class CrazyLoggerTest {

    @Test(expected = WrongDateException.class)
    public void testLogsByDate() throws WrongDateException {
        CrazyLogger crazyLogger = new CrazyLogger();
        crazyLogger.logsByDate(232, 56, 565);
    }
}
