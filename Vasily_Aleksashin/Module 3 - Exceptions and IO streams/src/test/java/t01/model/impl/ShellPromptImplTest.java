package t01.model.impl;

import org.junit.Test;
import t01.exception.ModelException;
import t01.model.ShellPrompt;

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
        String home = System.getProperty("user.home");
        String expected = name + "@" + host + " " + home + "\n> ";
		String actual = prompt.getPrompt();
		assertEquals(expected, actual);
	}

    @Test
    public void testSetCurrentDir() throws ModelException {
        ShellPrompt prompt = ShellPromptImpl.getInstance();
        prompt.setCurrentDir("c:");
        String actual = prompt.getCurrentDir();
        String expected = "c:";
        assertEquals(expected, actual);
    }

    @Test (expected = ModelException.class)
    public void testSetCurrentDirWithNull() throws ModelException {
        ShellPrompt prompt = ShellPromptImpl.getInstance();
        prompt.setCurrentDir(null);
        fail();
    }

    @Test (expected = ModelException.class)
    public void testSetCurrentDirWithEmptyPath() throws ModelException{
        ShellPrompt prompt = ShellPromptImpl.getInstance();
        prompt.setCurrentDir("");
        fail();
    }

    @Test
    public void testGetCurrentDir() {
        ShellPrompt prompt = ShellPromptImpl.getInstance();
        String expected = System.getProperty("user.home");
        String actual = prompt.getCurrentDir();
        assertEquals(expected, actual);
    }
}