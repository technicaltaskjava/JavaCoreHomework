package t01.model.impl;

import org.junit.Test;
import t01.model.ShellPrompt;

import static org.junit.Assert.*;

public class ShellPromptImplTest {
	
	@Test
	public void testGetPrompt() throws Exception {
		ShellPrompt prompt = new ShellPromptImpl();
		String expected = "aleksashin@ALEKSASHIN_PC C:\\Users\\aleksashin\n> ";
		String actual = prompt.getPrompt();
		assertEquals(expected, actual);
	}
}