package t01.model.directory.impl;

import org.junit.Test;
import t01.model.Environment;
import t01.model.directory.ShellPrompt;
import t01.model.directory.impl.ShellPromptImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ShellPromptImplTest {
	@Test
	public void testGetPrompt() throws UnknownHostException {
		ShellPrompt prompt = ShellPromptImpl.getInstance();
		String name = System.getProperty("user.name");
        String host = InetAddress.getLocalHost().getHostName();
        String home = Environment.getCurrentDir();
        String expected = name + "@" + host + " " + home + " > ";
		String actual = prompt.getPrompt();
		assertEquals(expected, actual);
	}
}