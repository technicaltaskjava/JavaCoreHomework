package t0304.model;

import org.junit.Before;
import org.junit.Test;
import t01.exception.ModelException;

import static org.junit.Assert.*;

public class ByteStreamTest {
	private UserStream stream;
	@Before
	public void setUp() {
		stream = new ByteStream();
	}

	@Test
	public void testRead() throws ModelException {
		String filePath = "src/test/resources/test_read.txt";
		String actual = stream.read(filePath);
		String expected = "Hello Java. You can read me.";
		assertEquals(expected, actual);
	}

	@Test(expected = ModelException.class)
	public void testReadWithNull() throws ModelException {
		stream.read(null);
	}

	@Test(expected = ModelException.class)
	public void testReadWrongFile() throws ModelException {
		String filePath = "test_read.txt";
		stream.read(filePath);
	}

	@Test
	public void testWrite() throws ModelException {
		String filePath = "src/test/resources/result_test.txt";
		String message = "for = 1\n" +
				"new = 1\n" +
				"if = 2\n" +
				"else = 1\n" +
				"import = 1\n" +
				"public = 2\n" +
				"return = 1\n" +
				"int = 10\n" +
				"static = 2\n" +
				"void = 1\n" +
				"class = 1\n" +
				"while = 1\n";
		stream.write(filePath, message);
	}

	@Test(expected = ModelException.class)
	public void testWriteWithFileIsNull() throws ModelException {
		String message = "";
		stream.write(null, message);
	}

	@Test(expected = ModelException.class)
	public void testWriteWithMessageIsNull() throws ModelException {
		String filePath = "src/test/resources/result_test.txt";
		stream.write(filePath, null);
	}
}