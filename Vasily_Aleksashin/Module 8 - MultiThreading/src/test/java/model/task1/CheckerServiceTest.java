package model.task1;

import exception.ParameterIncorrectException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckerServiceTest {
	private CheckerService service;

	@Test
	public void testGetPrimesCallableUpToTen() throws ParameterIncorrectException {
		service = new CheckerService(-1, 10, 2);
		service.findPrimeCallable();
		final List<Integer> primes = service.getPrimes();
		final String expected = "[2, 3, 5, 7]";
		assertEquals(expected, primes.toString());
	}

	@Test
	public void testGetPrimesCallableToOne() throws ParameterIncorrectException {
		service = new CheckerService(-1, 1, 2);
		service.findPrimeCallable();
		final List<Integer> primes = service.getPrimes();
		assertTrue(primes.isEmpty());
	}

	@Test
	public void testGetPrimesCallableToThree() throws ParameterIncorrectException {
		service = new CheckerService(2, 3, 1);
		service.findPrimeCallable();
		final List<Integer> primes = service.getPrimes();
		assertTrue(primes.size() == 2);
	}

	@Test
	public void testGetPrimesRunnableUpToTen() throws ParameterIncorrectException {
		service = new CheckerService(-1, 10, 3);
		service.findPrimeRunnable();
		final List<Integer> primes = service.getPrimes();
		final String expected = "[2, 3, 5, 7]";
		assertEquals(expected, primes.toString());
	}

	@Test
	public void testGetPrimesRunnableToOne() throws ParameterIncorrectException {
		service = new CheckerService(-1, 1, 2);
		service.findPrimeRunnable();
		final List<Integer> primes = service.getPrimes();
		assertTrue(primes.isEmpty());
	}

	@Test
	public void testGetPrimesRunnableToThree() throws ParameterIncorrectException {
		service = new CheckerService(2, 3, 1);
		service.findPrimeRunnable();
		final List<Integer> primes = service.getPrimes();
		assertTrue(primes.size() == 2);
	}

	@Test(expected = ParameterIncorrectException.class)
	public void testGetPrimesRunnableZero() throws ParameterIncorrectException {
		service = new CheckerService(2, 3, 0);
		service.findPrimeRunnable();
		final List<Integer> primes = service.getPrimes();
		assertTrue(primes.size() == 2);
	}
}