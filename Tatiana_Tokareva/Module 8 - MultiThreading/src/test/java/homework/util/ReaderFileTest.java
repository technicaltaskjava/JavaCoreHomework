package homework.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ReaderFileTest {

	@Test
	public void testRead() throws FileNotFoundException {
		String expected = ReaderFile.read(Constant.TEST_FILE_TXT);
		Assert.assertEquals("1:2:5\n", expected);

	}
	
}