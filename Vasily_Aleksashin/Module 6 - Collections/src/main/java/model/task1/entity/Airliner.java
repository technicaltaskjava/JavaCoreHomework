package model.task1.entity;

import exception.ParameterValidateException;

public class Airliner extends Aircraft {
	public Airliner(String model) throws ParameterValidateException {
		super(model);
	}

	@Override
	public void setTonnage(int tonnage) throws ParameterValidateException {
		super.setTonnage(0);
	}

	@Override
	public String toString() {
		return "Airliner{" + super.toString() + '}';
	}
}
