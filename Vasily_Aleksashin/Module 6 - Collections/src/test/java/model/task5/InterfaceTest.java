package model.task5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InterfaceTest {
	@Test
	public void testGetName() {
		assertEquals("Map", Interface.MAP.getName());
	}
}