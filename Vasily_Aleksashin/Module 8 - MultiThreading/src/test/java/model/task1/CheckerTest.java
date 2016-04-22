package model.task1;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertTrue;

public class CheckerTest {
	private Callable<List<Integer>> prime;

	@Test
	public void testCallToOne() throws Exception { //NOSONAR
		prime = new Checker(-1, 1);
		final List<Integer> primes = prime.call();
		assertTrue(primes.isEmpty());
	}

	@Test
	public void testCallToThree() throws Exception { //NOSONAR
		prime = new Checker(2, 3);
		final List<Integer> primes = prime.call();
		assertTrue(primes.size() == 2);
	}

	@Test
	public void testCallUpToTen() throws Exception { //NOSONAR
		prime = new Checker(-1, 10);
		final List<Integer> primes = prime.call();
		assertTrue(primes.size() == 4);
	}
}