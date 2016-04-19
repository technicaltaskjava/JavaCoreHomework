package homework.task1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CheckerTest {

	private List<Integer> collectionNumbers = Collections.synchronizedList(new ArrayList<Integer>());
	private Checker checker;

	@Test
	public void testSimpleNumbers() {
		Checker checker = new Checker(0, 5);
		List<Integer> numbers;
		checker.run();
		numbers = checker.getNumbers();
		final String expected = "[2, 3, 5]";
		assertEquals(expected, numbers.toString());
	}

	@Test
	public void testSimpleNumbersEmpty() {
		checker = new Checker(-2, 0);
		List<Integer> numbers;
		checker.run();
		numbers = checker.getNumbers();
		assertTrue(numbers.isEmpty());
	}

	@Test
	public void testCollectionNumbersEmpty() {
		checker = new Checker(-2, 0, collectionNumbers);
		checker.run();
		assertTrue(collectionNumbers.isEmpty());
	}

	@Test
	public void testCollectionNumbersNotEmpty() {
		checker = new Checker(0, 5, collectionNumbers);
		checker.run();
		final String expected = "[2, 3, 5]";
		assertEquals(expected, collectionNumbers.toString());
	}

}
