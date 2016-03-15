package task1;

import static org.junit.Assert.*;

import org.junit.Test;

import task1.ShowCatalogs;

public class ShowCatalogsTest {

	@Test
	public void testDisplayInfo() {
		
		ShowCatalogs show = new ShowCatalogs();
		String expected = "config.properties\n"+
				"javaWordsTask3.txt\n"+
				"javaWordsTask4.txt\n";
		assertEquals(expected, show.displayInfo());
	}

}
