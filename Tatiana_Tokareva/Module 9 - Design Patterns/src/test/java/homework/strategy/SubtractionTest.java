package homework.strategy;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class SubtractionTest {
	@Test
	public void testCount() {
		Subtraction subtraction = new Subtraction();
		int result = subtraction.count(4, 2);
		assertTrue(result == 2);
	}
	
}