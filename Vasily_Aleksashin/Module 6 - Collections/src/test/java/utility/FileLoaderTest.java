package utility;

import exception.FileIOException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileLoaderTest {
	
	private static final String EXPECTED = "Boeing 737|139|0|4670\n" +
			"Boeing 747|660|0|13570\n" +
			"Boeing 757|239|0|7240\n" +
			"Boeing 767|360|0|7680\n" +
			"Boeing 777|360|0|7680\n" +
			"Aerobus A310|255|0|9540\n" +
			"Aerobus A320|179|0|5185\n" +
			"Aerobus A330|440|0|8980\n" +
			"Aerobus A340|295|0|13350\n" +
			"Aerobus A380|555|0|15000\n" +
			"TU-134|76|0|2000\n" +
			"TU-334|102|0|3150\n" +
			"SU-80|30|0|1310\n" +
			"IL-76TD|0|50|3764\n" +
			"IL-114|0|7|515\n" +
			"IL-96-30|0|40|8460\n" +
			"IL-96-400T|0|92|4500\n" +
			"TU-154M|0|18|4670";
	private static final String FILE_NAME = "src/main/resources/aircrafts.txt";


	@Test
	public void testLoadFromTxtDefaultFile() throws FileIOException {
		final String expected = EXPECTED;
		assertEquals(expected, FileLoader.loadFromTxt(null));
	}

	@Test
	public void testLoadFromTxtCustomFile() throws FileIOException {
		final String expected = EXPECTED;
		assertEquals(expected, FileLoader.loadFromTxt(FILE_NAME));
	}
	
}