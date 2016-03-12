package t01.model.file;

import org.junit.Before;
import org.junit.Test;
import t01.exception.ModelException;
import t01.model.file.impl.FileOperationsImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FileOperationsTest {
    private static final String FILE_NAME_READ = "C:\\Users\\aleksashin\\IdeaProjects\\JavaCoreHomework\\Vasily_Aleksashin\\" +
            "Module 3 - Exceptions and IO streams\\src\\test\\resources\\test_read.txt";
    private static final String TEMP_FILE_NAME = "C:\\Users\\aleksashin\\IdeaProjects\\JavaCoreHomework\\Vasily_Aleksashin\\" +
            "Module 3 - Exceptions and IO streams\\src\\test\\resources\\test_temp.txt";
	private static final String FILE_NAME_WRITE = "C:\\Users\\aleksashin\\IdeaProjects\\JavaCoreHomework\\Vasily_Aleksashin\\" +
			"Module 3 - Exceptions and IO streams\\src\\test\\resources\\test_write.txt";
	private static final String MESSAGE_WRITE = "Hello Java! You can write me.\n";

	private FileOperations fileOperations;

    @Before
    public void setup() {
        fileOperations = new FileOperationsImpl();
    }

    @Test(expected = ModelException.class)
    public void testCreateFileWithFileNameIsNull() throws ModelException {
        fileOperations.create(null);
    }

    @Test(expected = ModelException.class)
    public void testCreateFileWithEmptyFileName() throws ModelException {
        fileOperations.create("");
    }

    @Test
    public void testCreateFileWithExists() throws ModelException {
        File file = new File(TEMP_FILE_NAME);
        try {
            if (file.exists()) {
                fileOperations.create(TEMP_FILE_NAME);
                fail();
            }
        } catch (ModelException e) {
            //test done
        }
    }

    @Test
    public void testCreateFileWithNotExists() throws ModelException {
        File file = new File(TEMP_FILE_NAME);
        if (!file.exists()) {
            fileOperations.create(TEMP_FILE_NAME);
        }
    }

    @Test(expected = ModelException.class)
    public void testDeleteFileWithFileNameIsNull() throws ModelException {
        fileOperations.delete(null);
    }

    @Test(expected = ModelException.class)
    public void testDeleteFileWithEmptyFileName() throws ModelException {
        fileOperations.delete("");
    }

    @Test
    public void testDeleteFileWithNotExists() throws ModelException {
        try {
            fileOperations.delete(TEMP_FILE_NAME);
        } catch (ModelException e) {
            //test done
        }
    }

    @Test
    public void testDeleteFileWithExists() throws ModelException {
        File file = new File(TEMP_FILE_NAME);
        if (file.exists()) {
            fileOperations.delete(TEMP_FILE_NAME);
        }
    }

    @Test(expected = ModelException.class)
    public void testReadFileWithFileNameIsNull() throws ModelException {
        fileOperations.read(null);

    }

    @Test(expected = ModelException.class)
    public void testReadFileWithFileNameIsEmpty() throws ModelException {
        fileOperations.read("");
    }

    @Test(expected = ModelException.class)
    public void testReadFileWithWrongFileName() throws ModelException {
        fileOperations.read("wrong.txt");
    }

    @Test
    public void testReadFileWithCorrectFileName() throws ModelException {
        String expected = "Hello Java. You can read me.";
        String actual = fileOperations.read(FILE_NAME_READ);
        assertEquals(expected, actual);
    }

	@Test (expected = ModelException.class)
	public void testWriteFileWithFileNameIsNull() throws ModelException {
		fileOperations.write(null, MESSAGE_WRITE, true);
	}

	@Test (expected = ModelException.class)
	public void testWriteFileWithFileNameIsEmpty() throws ModelException {
		fileOperations.write("", MESSAGE_WRITE, true);
	}

	@Test (expected = ModelException.class)
	public void testWriteFileWithWrongFileName() throws ModelException {
		fileOperations.write("wrong", MESSAGE_WRITE, true);
	}

	@Test (expected = ModelException.class)
	public void testWriteFileWithMessageIsNull() throws ModelException {
		fileOperations.write(FILE_NAME_WRITE, null, true);
	}

	@Test
	public void testWriteFileWithCorrectFileNameAndAppend() throws ModelException {
		String expected = oldFileState(FILE_NAME_WRITE) + MESSAGE_WRITE;
		fileOperations.write(FILE_NAME_WRITE, MESSAGE_WRITE, true);
		String actual = oldFileState(FILE_NAME_WRITE) + "\n";
		assertEquals(expected, actual);
	}

	@Test
	public void testWriteFileWithCorrectFileNameAndNotAppend() throws ModelException {
		String expected = MESSAGE_WRITE;
		fileOperations.write(FILE_NAME_WRITE, MESSAGE_WRITE, false);
		String actual = oldFileState(FILE_NAME_WRITE) + "\n";
		assertEquals(expected, actual);
	}

	private String oldFileState(final String fileName) throws ModelException {
		StringBuilder builder = new StringBuilder();
		String cache;
		try (FileReader in = new FileReader(fileName);
		     BufferedReader reader = new BufferedReader(in)) {
			while ((cache = reader.readLine()) != null) {
				builder.append(cache);
			}
		} catch (IOException e) {
			throw new ModelException(e.getMessage());
		}

		return builder.toString();
	}
}
