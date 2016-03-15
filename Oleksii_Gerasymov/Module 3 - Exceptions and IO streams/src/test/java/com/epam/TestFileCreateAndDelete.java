package test.java.com.epam;

import main.java.com.epam.t01.WorkWithFile;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.assertEquals;

public class TestFileCreateAndDelete {

    @Test
    public void createAndDeleteTxtFileTest() throws Exception {
        String testPath = WorkWithFile.getCurrentFilePath("temp");
        WorkWithFile.createTxtFile("temp");
        File currentFile = new File(testPath + ".txt");
        assertEquals(true, currentFile.exists());
        WorkWithFile.deleteTxtFile("temp");
        assertEquals(false, currentFile.exists());
    }
}
