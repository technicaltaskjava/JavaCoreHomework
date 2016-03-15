package task4;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

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

    ReadJavaCode rc = new ReadJavaCode();

    @Test
    public void testReadFile(){

        Map<String, Integer> theKeywordCount =  rc.readFile("testcode.txt");
        assertEquals(theKeywordCount.containsValue(4), true);
    }
    @Test
    public void testMapToString(){

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
        assertEquals(rc.mapToString(theKeywordCount), sb.toString());

    }
    @Test
    public void testCreateAndWriteFile() throws IOException {

        Map<String, Integer> keywordCount =  new HashMap<>();
        keywordCount.put("while", 2);
        System.out.println(rc.mapToString(keywordCount));
        rc.createAndWriteFile(rc.mapToString(keywordCount), "test4.txt");
        File file = new File("testAnswer.txt");
        File file2 = new File("test4.txt");
        assertEquals("The files differ!",
                FileUtils.readFileToString(file, "utf-8"),
                FileUtils.readFileToString(file2, "utf-8"));

    }
}
