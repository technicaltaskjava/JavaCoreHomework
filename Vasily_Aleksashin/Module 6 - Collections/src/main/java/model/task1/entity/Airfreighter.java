package model.task1.entity;

import exception.ParameterValidateException;

public class Airfreighter extends Aircraft {
	public Airfreighter(String model) throws ParameterValidateException {
		super(model);
	}

	@Override
	public void setCapacity(int capacity) throws ParameterValidateException {
		super.setCapacity(0);
	}

	@Override
	public String toString() {
		return "Airfreighter{" + super.toString() + '}';
	}
}
