package task1;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 21.03.2016
 */
public class VowelsProportionSorterTest {

    @Test
    public void testReadFile() throws FileNotFoundException {
        VowelsProportionSorter sorter = new VowelsProportionSorter();
        String result = sorter.readFile("Test1.txt");
        System.out.println(result);
        String expected = "создать  -  28,57 %\n" +
                          "Как  -  33,33 %\n" +
                          "строку  -  33,33 %\n";
        assertEquals("Result is wrong", result, expected);
    }
    @Test(expected = FileNotFoundException.class)
    public void testReadFileExc() throws FileNotFoundException {
        VowelsProportionSorter sorter = new VowelsProportionSorter();
        String result = sorter.readFile("Tt1.txt");

    }
}
