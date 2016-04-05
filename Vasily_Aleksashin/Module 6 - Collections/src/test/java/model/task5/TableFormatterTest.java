package model.task5;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TableFormatterTest {
	private Map<String, String> description = new HashMap<>();

	@Test
	public void testGetHeader() {
		String expected = "|Interface |Basic functionality" +
				"                                                        " +
				"|Examples of typical use                                                    |\n";
		assertEquals(expected, TableFormatter.getHeader());
	}

	@Test
	public void testGetTable() {
		description.put("List.functionality", "Test message");
		description.put("List.typicalUsage", "Test message");
		String expected = "|List      |Test message                    " +
				"                                           |Test message                       " +
				"                                        |\n";
		assertEquals(expected, TableFormatter.getTable(description, Interface.LIST, false));
	}

	@Test
	public void testReset() {
		try {
			TableFormatter.reset();
		} catch (Exception e) {
			fail();
		}
	}
	
}