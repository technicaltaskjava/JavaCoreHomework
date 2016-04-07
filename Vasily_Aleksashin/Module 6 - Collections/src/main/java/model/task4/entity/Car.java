package model.task4.entity;

import exception.GetInstanceException;

import java.util.HashSet;
import java.util.Set;

public class Car {
	private final String licensePlate;

	private Car(final String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public static Car getCar(final String licensePlate) throws GetInstanceException {
		if (licensePlate == null) {
			throw new GetInstanceException("License plate can not be NULL");
		}
		boolean validate = CarLicensePlateValidator.validate(licensePlate);
		if (validate) {
			return new Car(licensePlate);
		}
		throw new GetInstanceException(String.format("The car with license plate '%s' is already exist", licensePlate));
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	@Override
	public int hashCode() {
		return licensePlate != null ? licensePlate.hashCode() : 0;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Car car = (Car) o;
		return licensePlate != null ? licensePlate.equals(car.licensePlate) : car.licensePlate == null;
	}

	@Override
	public String toString() {
		return '[' + licensePlate + ']';
	}

	private static class CarLicensePlateValidator {
		private static final Set<String> licensePlates = new HashSet<>();

		private CarLicensePlateValidator() {
		}

		static boolean validate(final String licensePlate) {
			boolean contains = licensePlates.contains(licensePlate);
			if (!contains) {
				licensePlates.add(licensePlate);
				return true;
			}
			return false;
		}
	}
}
