package javafilereader;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexey Ushakov
 */
public class JavaByteReaderTest {

    @Test
    public void testReedWordsFromTXT() throws Exception {
        JavaByteReader reader = new JavaByteReader("./src/test/java/javafilereader/reservedWords.txt");
        reader.checkReservedWord();
        assertTrue(reader.getReservedWordsCount() == 16);
    }

    @Test
    public void testReedWordsFromJAVA() throws Exception {
        JavaByteReader reader = new JavaByteReader("./src/test/java/javafilereader/reservedWords.java");
        reader.checkReservedWord();
        assertTrue(reader.getReservedWordsCount() == 18);
    }

    @Test
    public void testReedAllWordsFromJAVA() throws Exception {
        JavaByteReader reader = new JavaByteReader("./src/test/java/javafilereader/reservedWordsAll.java");
        reader.checkReservedWord();
        assertTrue(reader.getReservedWordsCount() == 45);
    }
}