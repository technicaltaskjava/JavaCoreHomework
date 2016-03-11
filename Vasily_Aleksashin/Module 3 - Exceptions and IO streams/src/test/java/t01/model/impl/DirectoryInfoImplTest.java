package t01.model.impl;

import org.junit.Before;
import org.junit.Test;
import t01.exception.ModelException;
import t01.model.DirectoryInfo;

import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DirectoryInfoImplTest {
    private DirectoryInfo directoryInfo;

    @Before
    public void setUp() throws Exception {
        directoryInfo = new DirectoryInfoImpl();
    }

    @Test
    public void testGetDirStream() throws ModelException {
        String testDir = System.getProperty("user.home");
        Path[] dirStream = directoryInfo.getDirStream(testDir);
        assertNotNull(dirStream);
    }

    @Test (expected = ModelException.class)
    public void testGetDirStreamWithNull() throws ModelException {
        directoryInfo.getDirStream(null);
    }

    @Test (expected = ModelException.class)
    public void testGetDirStreamWithWrongPath() throws ModelException {
        directoryInfo.getDirStream("w");
    }

    @Test (expected = ModelException.class)
    public void testGetDirInfoWithNull() throws ModelException {
        directoryInfo.getDirInfo(null);
    }

    @Test
    public void testGetDirInfoWithEmptyPaths() throws ModelException {
        String info = directoryInfo.getDirInfo(new Path[0]);
        String expected = "\t\t\t\t0 directories\n" +
                "\t\t\t\t0 files        0 byte";
        assertEquals(expected, info);
    }
}