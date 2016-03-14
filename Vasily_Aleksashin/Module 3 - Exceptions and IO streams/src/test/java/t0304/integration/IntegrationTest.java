package t0304.integration;

import org.junit.Test;
import t01.view.impl.ConsoleMock;
import t0304.Main;


import static org.junit.Assert.assertEquals;

public class IntegrationTest {
	private ConsoleMock console = new ConsoleMock();

	@Test
	public void testExit() {
		console.addIn("4");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load file with keywords [2] Found keywords by ByteStream [3] Found keywords by CharStream [4] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testLoadArgs() {
		console.addIn("4");
		String[] args = new String[] {"wrong"};
		Main.main(args);
		String expected = "=======================================================\r\n" +
				"App can not load keywords from file wrong\r\n" +
				"\tFile not found\r\n" +
				"=======================================================\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load file with keywords [2] Found keywords by ByteStream [3] Found keywords by CharStream [4] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testLoad() {
		console.addIn("1");
		console.addIn("src/test/resources/java_keywords.txt");
		console.addIn("4");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load file with keywords [2] Found keywords by ByteStream [3] Found keywords by CharStream [4] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter file path:\r\n" +
				"Operation successful\n" +
				"\tTotal load 50 keywords\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load file with keywords [2] Found keywords by ByteStream [3] Found keywords by CharStream [4] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testSearchAsByteStream() {
		console.addIn("2");
		console.addIn("src/test/resources/keyword_test.txt");
		console.addIn("4");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load file with keywords [2] Found keywords by ByteStream [3] Found keywords by CharStream [4] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter java file path:\r\n" +
				"Search is successful\n" +
				"\tKeywords:\n" +
				"for = 1\n" +
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
				"while = 1\n" +
				"\r\n" +
				"Result of search successful write to file:\n" +
				"\t'C:\\Users\\aleksashin\\IdeaProjects\\JavaCoreHomework\\Vasily_Aleksashin\\Module 3 - Exceptions and IO streams\\src\\test\\resources\\result.txt'\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load file with keywords [2] Found keywords by ByteStream [3] Found keywords by CharStream [4] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testSearchAsCharStream() {
		console.addIn("3");
		console.addIn("src/test/resources/keyword_test.txt");
		console.addIn("4");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load file with keywords [2] Found keywords by ByteStream [3] Found keywords by CharStream [4] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter java file path:\r\n" +
				"Search is successful\n" +
				"\tKeywords:\n" +
				"for = 1\n" +
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
				"while = 1\n" +
				"\r\n" +
				"Result of search successful write to file:\n" +
				"\t'C:\\Users\\aleksashin\\IdeaProjects\\JavaCoreHomework\\Vasily_Aleksashin\\Module 3 - Exceptions and IO streams\\src\\test\\resources\\result.txt'\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load file with keywords [2] Found keywords by ByteStream [3] Found keywords by CharStream [4] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}
}
