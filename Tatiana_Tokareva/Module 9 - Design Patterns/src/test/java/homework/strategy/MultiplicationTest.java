package homework.strategy;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class MultiplicationTest {
	@Test
	public void testCount() {
		Multiplication multiplication = new Multiplication();
		int result = multiplication.count(2, 4);
		assertTrue(result == 8);
	}
	
}