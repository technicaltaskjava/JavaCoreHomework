package model.task1.entity;

import exception.ParameterValidateException;
import utility.Validator;

public abstract class Aircraft {
	private final String model;
	private int capacity;
	private int tonnage;
	private int flyingRange;

	Aircraft(String model) throws ParameterValidateException {
		Validator.isNull(model, "Model");
		this.model = model;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) throws ParameterValidateException {
		Validator.isPositive(capacity, "Capacity");
		this.capacity = capacity;
	}

	public int getFlyingRange() {
		return flyingRange;
	}

	public void setFlyingRange(int flyingRange) throws ParameterValidateException {
		Validator.isPositive(capacity, "Flying range");
		this.flyingRange = flyingRange;
	}

	public String getModel() {
		return model;
	}

	public int getTonnage() {
		return tonnage;
	}

	public void setTonnage(int tonnage) throws ParameterValidateException {
		Validator.isPositive(capacity, "Tonnage");
		this.tonnage = tonnage;
	}

	@Override
	public int hashCode() {
		return model.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Aircraft aircraft = (Aircraft) o;
		return model.equals(aircraft.model);
	}

	@Override
	public String toString() {
		return "model='" + model + '\'';
	}
}
