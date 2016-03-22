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
public class WordRemoverTest {

    @Test
    public void testReadFile() throws FileNotFoundException, WrongCharException {
        WordRemover remover = new WordRemover();
        String result = remover.readFile("Test1.txt", 'с', 6);
        System.out.println(result);
        String expected = "Как создать .\n";
        assertEquals("Result is wrong", result, expected);
    }
    @Test(expected = FileNotFoundException.class)
    public void testReadFileExc() throws FileNotFoundException, WrongCharException {
        WordRemover remover = new WordRemover();
        String result = remover.readFile("T1.txt", 'с', 6);

    }

}
