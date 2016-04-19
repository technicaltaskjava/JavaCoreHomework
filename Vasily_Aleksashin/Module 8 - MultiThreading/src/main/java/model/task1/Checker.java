package model.task1;

import exception.ParameterIncorrectException;
import utility.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

class Checker implements Callable<List<Integer>>, Runnable {
	private final int lowerBound;
	private final int upperBound;
	private List<Integer> primes;

	Checker(final int lowerBound, final int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		primes = new ArrayList<>();
	}

	Checker(final int lowerBound, final int upperBound, final List<Integer> primes) throws ParameterIncorrectException {
		this(lowerBound, upperBound);
		Validator.isNull(primes);
		this.primes = primes;
	}

	@Override
	public void run() {
		for (int index = lowerBound; index <= upperBound; index++) {
			if (isPrime(index)) {
				primes.add(index);
			}
		}
	}

	@Override
	public List<Integer> call() throws Exception {
		for (int index = lowerBound; index <= upperBound; index++) {
			if (isPrime(index)) {
				primes.add(index);
			}
		}
		return primes.isEmpty() ? Collections.<Integer>emptyList() : primes;
	}

	private boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}
		if (number < 4) {
			return true;
		}
		if (number % 2 == 0) {
			return false;
		}
		for (int index = 3; index * index <= number; index += 2) {
			if (number % index == 0) {
				return false;
			}
		}
		return true;
	}
}
