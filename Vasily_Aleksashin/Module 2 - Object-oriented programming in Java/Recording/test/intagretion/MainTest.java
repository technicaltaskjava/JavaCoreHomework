package intagretion;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

public class MainTest {
	private UserInputStream in;
	private UserOutputStream out;

	@Before
	public void setup() {
		in = new UserInputStream();
		out = new UserOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
	}

	@Test
	public void testNotFoundLibraryFile() {
		in.add("n");
		in.add("filename.txt");
		in.add("n");

//		Main.main(new String[0]);

		String expected = "Hello user!\r\n" +
				"To close the application, enter the EXIT\r\n" +
				"The application must download the media library from file\r\n" +
				"Do you want to load default media library? (y/n):\r\n" +
				"Enter media library file name:\r\n" +
				"Something gone wrong, can not download the Media Library\r\n" +
				"Would you like to try again? (y/n)\r\n" +
				"Thank you for using my APP\r\n";

//		assertEquals(expected, out.getLine());
	}
}
