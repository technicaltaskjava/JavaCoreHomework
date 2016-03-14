package t01.model;

import org.junit.Test;
import t01.exception.ModelException;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class EnvironmentTest {
    @Test
    public void testPreparedPath() throws ModelException {
        Environment.setCurrentDir("c:");
        Path path = Environment.preparedPath("windows");
        Path expected = Paths.get("c:\\windows");
        assertEquals(expected, path);
    }
}