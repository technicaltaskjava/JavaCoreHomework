package filemanager;

import org.junit.Before;
import org.junit.Test;
import filemanager.exceptions.FileNotCreatedException;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexey Ushakov
 */
public class FileManagerTest {
    private FileManager manager;

    @Before
    public void start() {
        manager = new FileManager();
    }

    @Test
    public void testCreateFile() throws Exception {
        File file = new File("./src/test/java/filemanager/testCreateManagerFile");
        manager.createFile(file);
        assertTrue(file.exists());
        file.delete();
    }

    @Test(expected = FileNotCreatedException.class)
    public void testCreateBrokenFile() throws Exception {
        File file = new File("./src/test/java/filemanager//////");
        manager.createFile(file);
    }

    @Test
    public void testDeleteFile() throws Exception {
        File file = new File("./src/test/java/filemanager//testDeleteManagerFile");
        file.createNewFile();
        manager.deleteFile(file);
        assertFalse(file.exists());
    }

    @Test(expected = FileNotFoundException.class)
    public void testDeleteNotExistFile() throws Exception {
        File file = new File("./src/test/java/filemanager/testDeleteManagerFile");
        manager.deleteFile(file);
    }
}