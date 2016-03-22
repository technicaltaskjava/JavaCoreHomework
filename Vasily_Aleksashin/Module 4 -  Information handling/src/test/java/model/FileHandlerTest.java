package model;

import exeption.FileIOException;
import exeption.ParameterIsNullException;
import org.junit.Before;
import org.junit.Test;

public class FileHandlerTest {
	private FileHandler fileHandler;

	@Before
	public void setUp() {
		fileHandler = new FileHandler();
	}

	@Test(expected = ParameterIsNullException.class)
	public void testReadWithNull() throws FileIOException, ParameterIsNullException {
		fileHandler.read(null);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testCreateWithNull() throws FileIOException, ParameterIsNullException {
		fileHandler.create(null);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testWriteWithFileIsNull() throws FileIOException, ParameterIsNullException {
		fileHandler.write(null, "message");
	}

	@Test(expected = ParameterIsNullException.class)
	public void testWriteWithMessageIsNull() throws FileIOException, ParameterIsNullException {
		fileHandler.write("file.txt", null, true);
	}
}