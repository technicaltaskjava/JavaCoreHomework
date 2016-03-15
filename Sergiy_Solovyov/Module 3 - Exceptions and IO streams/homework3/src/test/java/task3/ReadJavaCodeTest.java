package task3;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import task4.ReadJavaCode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 13.03.2016
 */
public class ReadJavaCodeTest {

    @Test
    public void testReadFile(){
        task4.ReadJavaCode readJavaCode = new task4.ReadJavaCode();
        Map<String, Integer> theKeywordCount =  readJavaCode.readFile("testcode.txt");
        assertEquals(theKeywordCount.containsValue(4), true);
    }
    @Test
    public void testMapToString(){
        task4.ReadJavaCode readJavaCode = new ReadJavaCode();
        Map<String, Integer> theKeywordCount =  new HashMap<>();
        theKeywordCount.put("while", 2);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : theKeywordCount.entrySet())
        {   int value = entry.getValue();
            if (value != 0){
                sb.append(entry.getKey());
                sb.append(" = ");
                sb.append(value);
                sb.append("\n");}
        }
        assertEquals(readJavaCode.mapToString(theKeywordCount), sb.toString());

    }
    @Test
    public void testCreateAndWriteFile() throws IOException {
        ReadJavaCode readJavaCode = new ReadJavaCode();
        Map<String, Integer> keywordCount =  new HashMap<>();
        keywordCount.put("while", 2);
        System.out.println(readJavaCode.mapToString(keywordCount));
        readJavaCode.createAndWriteFile(readJavaCode.mapToString(keywordCount), "test3.txt");
        File file = new File("testAnswer.txt");
        File file2 = new File("test3.txt");
        assertEquals("The files differ!",
                FileUtils.readFileToString(file, "utf-8"),
                FileUtils.readFileToString(file2, "utf-8"));

    }
}
