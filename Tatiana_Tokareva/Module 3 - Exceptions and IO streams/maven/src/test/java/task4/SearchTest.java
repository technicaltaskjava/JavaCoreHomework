package task4;


import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import task4.Search;

public class SearchTest {

	
		private static final String FILE_NAME = "src\\test\\resources\\javaWordsTask4.txt";
		private static final String TEXT = "int";
		
	@Test
		
		public void testWrite()throws FileNotFoundException {

		Search show= new Search();
			
			Assert.assertTrue(show.write(FILE_NAME,TEXT,false));
		}
	
	@Test
	public void testRead() {
		Search show= new Search();
		String actual = show.read(FILE_NAME);
		Assert.assertEquals("int", actual);
	}

}
