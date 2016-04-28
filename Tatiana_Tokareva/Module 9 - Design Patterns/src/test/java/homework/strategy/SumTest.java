package homework.strategy;

import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class SumTest {
	@Test
	public void testCount() {
		Sum sum = new Sum();
		int result = sum.count(4, 3);
		assertTrue(result == 7);

	}
	
}