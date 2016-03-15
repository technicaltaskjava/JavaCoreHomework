package javafilereader;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Alexey Ushakov
 */
public class JavaCharReaderTest {

    @Test
    public void testReedWordsFromTXT() throws Exception {
        JavaCharReader reader = new JavaCharReader("./src/test/java/javafilereader/reservedWords.txt");
        reader.checkReservedWord();
        assertTrue(reader.getReservedWordsCount() == 16);
    }

    @Test
    public void testReedWordsFromJAVA() throws Exception {
        JavaCharReader reader = new JavaCharReader("./src/test/java/javafilereader/reservedWords.java");
        reader.checkReservedWord();
        assertTrue(reader.getReservedWordsCount() == 18);
    }

    @Test
    public void testReedAllWordsFromJAVA() throws Exception {
        JavaCharReader reader = new JavaCharReader("./src/test/java/javafilereader/reservedWordsAll.java");
        reader.checkReservedWord();
        assertTrue(reader.getReservedWordsCount() == 45);
    }
}