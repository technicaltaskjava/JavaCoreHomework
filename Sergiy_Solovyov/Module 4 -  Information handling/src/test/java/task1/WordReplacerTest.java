package task1;

import console.WrongCharException;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 21.03.2016
 */
public class WordReplacerTest {
    @Test
    public void testReadFile() throws FileNotFoundException, NoSuchFieldException {
        WordReplacer replacer = new WordReplacer();
       String result = replacer.readFile("Test1.txt");
        String expected = "строку создать Как.\n";
        assertEquals("Result is wrong", expected, result);
    }
    @Test(expected = FileNotFoundException.class)
    public void testReadFileExc() throws FileNotFoundException, WrongCharException {
        WordRemover remover = new WordRemover();
        String result = remover.readFile("T1.txt", 'с', 6);

    }
}
