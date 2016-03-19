package task1;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import task1.exceptions.FileUpdateException;
import task1.fileview.FileViewer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 14.03.2016
 */
public class FileViewerTest {

    private static FileViewer fileViewer = new FileViewer();
    private static String path ;


    @Before
    public void testWriteTXT() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {

        Method method = FileViewer.class.getDeclaredMethod("writeTXT", String.class, String.class);
        method.setAccessible(true);
        method.invoke(fileViewer, path, "note");
    }

    @After
    public void testFileDelete() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {

        Method method = FileViewer.class.getDeclaredMethod("fileDelete", String.class);
        method.setAccessible(true);
        assertEquals("File was not deleted", method.invoke(fileViewer, path), true);
    }

    @Test
    public void testIsInMenu() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int[] arr = new int[2];
        Method method = FileViewer.class.getDeclaredMethod("isInMenu", int[].class, int.class);
        method.setAccessible(true);
        assertEquals("Method must return false", method.invoke(fileViewer, arr, 9), false);
        arr[0] = 3;
        assertEquals("Method must return true", method.invoke(fileViewer, arr, 3), true);

    }

    static {
        Method method = null;
        try {
            method = FileViewer.class.getDeclaredMethod("createTXT", String.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        method.setAccessible(true);
        File file = null;
        try {
            file = (File) method.invoke(fileViewer, System.getProperty("user.dir"), "test1");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        path =  file.getAbsolutePath();
    }


}