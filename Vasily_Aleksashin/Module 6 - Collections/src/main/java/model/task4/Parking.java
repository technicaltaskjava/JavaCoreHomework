package model.task4;

import exception.GetInstanceException;
import exception.ParameterValidateException;
import model.task4.entity.Car;
import utility.Validator;

public class Parking {
	private static Parking instance = null;

	private final Car[] places;

	private Parking(final int size) {
		places = new Car[size];
	}

	public static Parking getInstance(final int size) throws GetInstanceException {
		if (size < 1) {
			throw new GetInstanceException("Parking size can not be less than 1");
		}
		if (instance == null) {
			instance = new Parking(size);
		}
		return instance;
	}

	boolean coming(final Car car) throws ParameterValidateException {
		return coming(0, car);
	}

	public boolean coming(final int position, final Car car) throws ParameterValidateException {
		Validator.isNull(car, "Car");
		if (findCar(car) >= 0) {
			return false;
		}
		final int index = findCar(position, null);
		if (index < 0) {
			return false;
		}
		places[index] = car;
		return true;
	}

	public boolean leaving(final int position, final Car car) throws ParameterValidateException {
		Validator.isNull(car, "Car");
		final int index = findCar(position, car);
		if (index < 0) {
			return false;
		}
		places[index] = null;
		return true;
	}

	boolean leaving(final Car car) throws ParameterValidateException {
		return leaving(0, car);
	}

	public int freePlacesCount() {
		int count = 0;
		for (Car car : places) {
			if (car == null) {
				count++;
			}
		}
		return count;
	}

	public int getPlaceNumber(final Car car) throws ParameterValidateException {
		Validator.isNull(car, "Car");
		return findCar(car);
	}

	public boolean changePlace(final Car car, final int newPlace) throws ParameterValidateException {
		Validator.isNull(car, "Car");
		Validator.isInBoundary(newPlace, places.length - 1, "Number of places");
		int currentPlace = findCar(car);
		if (currentPlace == -1 || places[newPlace] != null) {
			return false;
		}
		places[currentPlace] = null;
		places[newPlace] = car;
		return true;
	}

	public String getParking() {
		StringBuilder builder = new StringBuilder();
		int size = places.length / 2;
		for (int index = 0; index < places.length; index++) {
			String place = places[index] != null ? places[index].toString() : String.format("[%-8s]", "");
			builder.append(place).append(" ");
			if (index != 0 && index % size == 0) {
				builder.append("\n");
			}
		}
		return builder.toString();
	}

	@Override
	public String toString() {
		return getParking();
	}

	private int findCar(final Car car) throws ParameterValidateException {
		return findCar(0, car);
	}

	private int findCar(final int position, final Car car) throws ParameterValidateException {
		Validator.isInBoundary(position, places.length - 1, "Number of places");
		for (int index = position; index < places.length; index++) {
			if (places[index] == car) {
				return index;
			}
		}
		for (int index = 0; index < position; index++) {
			if (places[index] == car) {
				return index;
			}
		}
		return -1;
	}
}
