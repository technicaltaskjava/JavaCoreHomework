package t02.integration;

import org.junit.Test;
import t01.view.impl.ConsoleMock;
import t02.Main;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
	private ConsoleMock console = new ConsoleMock();

	@Test
	public void testExit() {
		console.addIn("7");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testLoad() {
		console.addIn("1");
		console.addIn("src\\main\\resources\\various.properties");
		console.addIn("n");
		console.addIn("7");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter file name:\r\n" +
				"Default separator for key/value is '='. Do you want change this? (y/n)\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testLoadAndPrint() {
		console.addIn("1");
		console.addIn("src\\main\\resources\\various.properties");
		console.addIn("n");
		console.addIn("2");
		console.addIn("7");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter file name:\r\n" +
				"Default separator for key/value is '='. Do you want change this? (y/n)\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Properties{\n" +
				"Property{key='key1', value='value1'}\n" +
				"Property{key='key2', value='value2'}\n" +
				"Property{key='key3', value='value3'}\n" +
				"Property{key='key4', value='value4'}\n" +
				"Property{key='key5', value='value5'}\n" +
				"Property{key='key6', value='value6'}\n" +
				"Property{key='key7', value='value7'}\n" +
				"Property{key='key8', value='value8'}\n" +
				"Property{key='key9', value='value9'}\n" +
				"}\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testLoadAndPrintValue() {
		console.addIn("1");
		console.addIn("src\\main\\resources\\various.properties");
		console.addIn("n");
		console.addIn("3");
		console.addIn("key4");
		console.addIn("7");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter file name:\r\n" +
				"Default separator for key/value is '='. Do you want change this? (y/n)\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter key:\r\n" +
				"For key 'key4' value is 'value4'\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testLoadAndAdd() {
		console.addIn("1");
		console.addIn("src\\main\\resources\\various.properties");
		console.addIn("n");
		console.addIn("4");
		console.addIn("key10");
		console.addIn("value10");
		console.addIn("7");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter file name:\r\n" +
				"Default separator for key/value is '='. Do you want change this? (y/n)\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter new key:\r\n" +
				"Enter new value:\r\n" +
				"Operation successful\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testLoadAndUpdate() {
		console.addIn("1");
		console.addIn("src\\main\\resources\\various.properties");
		console.addIn("n");
		console.addIn("5");
		console.addIn("key5");
		console.addIn("value for test");
		console.addIn("7");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter file name:\r\n" +
				"Default separator for key/value is '='. Do you want change this? (y/n)\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter key:\r\n" +
				"Enter new value:\r\n" +
				"Operation successful\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testLoadAndRemove() {
		console.addIn("1");
		console.addIn("src\\main\\resources\\various.properties");
		console.addIn("n");
		console.addIn("6");
		console.addIn("key5");
		console.addIn("7");
		Main.main(new String[0]);
		String expected = "=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter file name:\r\n" +
				"Default separator for key/value is '='. Do you want change this? (y/n)\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Enter key:\r\n" +
				"Operation successful\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}

	@Test
	public void testLoadFromArgs() {
		console.addIn("7");
		String[] args = new String[] {"src\\test\\resources\\test.properties"};
		Main.main(args);
		String expected = "=======================================================\r\n" +
				"Start loading properties file(s) from command arguments...\r\n" +
				"=======================================================\r\n" +
				"=======================================================\r\n" +
				"Loading finished successful:\r\n" +
				"\ttotal file: 1,\r\n" +
				"\tloading file: 1,\r\n" +
				"=======================================================\r\n" +
				"=======================================================\r\n" +
				"Menu:\r\n" +
				"=======================================================\r\n" +
				"[1] Load from file [2] Print all key/value [3] Print value by key " +
				"[4] Add new key/value [5] Update value by key [6] Remove key/value [7] Exit\r\n" +
				"=======================================================\r\n" +
				"Enter menu number:\r\n" +
				"Goodbye\r\n";
		assertEquals(expected, console.getOut());
	}
}
