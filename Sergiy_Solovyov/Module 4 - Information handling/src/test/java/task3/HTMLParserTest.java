package task3;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 21.03.2016
 */
public class HTMLParserTest {

    @Test
    public void testParseHTML() throws FileNotFoundException {
        HTMLParser htmlParser = new HTMLParser();
        String result = htmlParser.parseHTML("Test_3.html");
        String expected = "На рисунке красиво.\n";
        assertEquals("Result is wrong", result, expected);
    }
    @Test(expected = FileNotFoundException.class)
    public void testParseHTMLException() throws FileNotFoundException {
        HTMLParser htmlParser = new HTMLParser();
        String result = htmlParser.parseHTML("Tet_3.html");

    }
}
