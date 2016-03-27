package task1;

import org.junit.Test;
import task3.HTMLParser;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 21.03.2016
 */
public class LetterRemoverTest {
    @Test
    public void testReadAndEditFile() throws FileNotFoundException {
        LetterRemover letterRemover = new LetterRemover();
       String result = letterRemover.readAndEditFile("Test1.txt");
        System.out.println(result);
        String expected = "Ка создать строку.\n";
        assertEquals("Result is wrong", result, expected);
    }
    @Test(expected = FileNotFoundException.class)
    public void testParseHTMLException() throws FileNotFoundException {
        LetterRemover letterRemover = new LetterRemover();
        String result = letterRemover.readAndEditFile("dfss.txt");

    }
}
