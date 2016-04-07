package model.task5;

import exception.FileIOException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DescriptionTest {
	private static final String FILE_NAME = "src/main/resources/description_of_collections.properties";

	@Test(expected = FileIOException.class)
	public void testGetDescriptionWithNull() throws FileIOException {
		Description.getDescription(null);
	}

	@Test
	public void testGetDescriptionWithFileName() throws FileIOException {
		assertNotNull(Description.getDescription(FILE_NAME));
	}

	@Test(expected = FileIOException.class)
	public void testGetDescriptionWithWrongFileName() throws FileIOException {
		Description.getDescription("");
	}
}