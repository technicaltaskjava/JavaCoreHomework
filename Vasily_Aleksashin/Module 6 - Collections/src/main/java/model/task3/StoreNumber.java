package model.task3;

import exception.ParameterValidateException;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class StoreNumber {
	private final List<Double> store = new ArrayList<>();

	public boolean add(Double element) throws ParameterValidateException {
		validate(element);
		return !store.contains(element) && store.add(element);
	}

	public boolean remove(Double element) throws ParameterValidateException {
		validate(element);
		return store.remove(element);
	}

	public Double find(Double element) throws ParameterValidateException {
		validate(element);
		double residual = Double.MAX_VALUE;
		double target = Double.MIN_VALUE;
		if (store.isEmpty()) {
			return null;
		}
		for (Double d : store) {
			final double abs = Math.abs(element - d);
			if (abs < residual) {
				residual = abs;
				target = d;
			}
		}
		return target;
	}

	public List<Double> getStore() {
		return unmodifiableList(store);
	}

	private void validate(final Double element) throws ParameterValidateException {
		if (element == null) {
			throw new ParameterValidateException("Parameter can not be NULL");
		}
	}


}
