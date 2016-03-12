package t01.controller;

import org.junit.Test;
import t01.Main;
import t01.model.Environment;
import t01.view.impl.ConsoleMock;

import static org.junit.Assert.assertEquals;

public class MainControllerTest {
	private ConsoleMock console = new ConsoleMock();
	private String user = Environment.getUserName();
	private String host = Environment.getHostName();
	private String dir = Environment.getCurrentDir();

	@Test
	public void testExit() {
		console.addIn("exit");
		Main.main(new String[0]);
		String expected = "The application does not recognize Cyrillic\n" +
				"Enter command or help\r\n" +
				user + "@" + host + " " + dir + " > ";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testHelp() {
		console.addIn("help");
		console.addIn("exit");
		Main.main(new String[0]);
		String expected = "The application does not recognize Cyrillic\n" +
				"Enter command or help\r\n" +
				user + "@" + host + " " + dir + " > Available Commands:\n" +
				"dir\n" +
				"\tOutput the contents from the current directory to the screen\n" +
				"cd\n" +
				"\tSyntax: cd [path]\n" +
				"\t\t[path] relative path to the target directory from the current directory\n" +
				"\t\t.. indicates the parent directory.  Example: cd ..      - move up\n" +
				"\t\t.  indicates the current directory. Example: cd .\\dir  - goto dir in current path\n" +
				"\tChange the current directory\n" +
				"newFile\n" +
				"\tSyntax: newFile [[path]fileName]\n" +
				"\t\t[path] relative path to the file from the current directory\n" +
				"\tCreate new empty file in current directory\n" +
				"read\n" +
				"\tSyntax: read [[path]fileName]\n" +
				"\t\t[path] relative path to the file from the current directory\n" +
				"\tRead file in current directory\n" +
				">\n" +
				"\tSyntax: [text] > [[path]fileName] [-a]\n" +
				"\t\t[text] text that should be added to the file\n" +
				"\t\t[path] relative path to the file from the current directory\n" +
				"\t\t[-a]  command-line option, which performs the recording the text to the end of the file\n" +
				"\tWrite file in current directory\n" +
				"exit\n" +
				"\tExit from application\r\n" +
				user + "@" + host + " " + dir + " > ";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testWrongCommand() {
		console.addIn("wrong");
		console.addIn("exit");
		Main.main(new String[0]);
		String expected = "The application does not recognize Cyrillic\n" +
				"Enter command or help\r\n" +
				user + "@" + host + " " + dir + " > " +
				"\tEnter wrong command\r\n" +
				user + "@" + host + " " + dir + " > ";
		assertEquals(expected, console.getOut());
	}
}