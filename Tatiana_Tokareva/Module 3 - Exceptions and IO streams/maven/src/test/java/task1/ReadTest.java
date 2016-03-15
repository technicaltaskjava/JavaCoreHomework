package task1;


import org.junit.Assert;
import org.junit.Test;

import task1.Read;

public class ReadTest {

	private static final String FILE_NAME = "src\\test\\resources\\test_file.txt";

	@Test
	public void testRead() {
		Read reader = new Read();
		String actual = reader.read(FILE_NAME);
		Assert.assertEquals("some text", actual);
	}
}
