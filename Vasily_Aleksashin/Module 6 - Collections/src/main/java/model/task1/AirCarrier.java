package model.task1;

import exception.ParameterValidateException;
import model.task1.entity.Aircraft;
import model.task1.entity.Airfreighter;
import model.task1.entity.Airliner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Validator;

import java.util.*;

public class AirCarrier {
	private static final Logger logger = LoggerFactory.getLogger(AirCarrier.class);
	private static final String DEFAULT_SEPARATOR = "[|]";

	private final List<Aircraft> aircrafts = new ArrayList<>();

	public int load(final String aircraftData) {
		final String regex = DEFAULT_SEPARATOR;
		int count = 0;
		try (Scanner scanner = new Scanner(aircraftData)) {
			String line;
			Aircraft aircraft;
			String[] split;
			String model;
			int capacity;
			int tonnage;
			int flyingRange;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				split = line.split(regex);
				model = split[0];
				capacity = parsInt(split[1]);
				tonnage = parsInt(split[2]);
				flyingRange = parsInt(split[3]);
				if (capacity > 0) {
					aircraft = new Airliner(model);
				} else {
					aircraft = new Airfreighter(model);
				}
				count++;
				aircraft.setCapacity(capacity);
				aircraft.setTonnage(tonnage);
				aircraft.setFlyingRange(flyingRange);
				aircrafts.add(aircraft);
			}
		} catch (ParameterValidateException e) {
			logger.error(e.getMessage(), e);
		}
		return count;
	}

	public boolean add(final Aircraft aircraft) throws ParameterValidateException {
		Validator.isNull(aircraft, "Aircraft");
		return aircrafts.add(aircraft);
	}

	public boolean remove(final Aircraft aircraft) throws ParameterValidateException {
		Validator.isNull(aircraft, "Aircraft model");
		return aircrafts.remove(aircraft);
	}

	public int getCapacity() {
		int capacity = 0;
		for (Aircraft aircraft : aircrafts) {
			capacity += aircraft.getCapacity();
		}
		return capacity;
	}

	public int getTonnage() {
		int tonnage = 0;
		for (Aircraft aircraft : aircrafts) {
			tonnage += aircraft.getTonnage();
		}
		return tonnage;
	}

	public void sort(final Comparator<Aircraft> comparator) {
		Collections.sort(aircrafts, comparator);
	}

	public List<Aircraft> find(final String model, final int capacity, final int tonnage, final int flyingRange) {
		List<Aircraft> searchResult = new ArrayList<>();
		for (Aircraft aircraft : aircrafts) {
			if (aircraft != null) {
				Boolean[] flags = new Boolean[]{null, null, null, null};
				if (model != null) {
					flags[0] = aircraft.getModel().equals(model);
				}
				if (capacity > 0) {
					flags[1] = aircraft.getCapacity() == capacity;
				}
				if (tonnage > 0) {
					flags[2] = aircraft.getTonnage() == tonnage;
				}
				if (flyingRange > 0) {
					flags[3] = aircraft.getFlyingRange() == flyingRange;
				}

				boolean flag = true;
				for (Boolean elements : flags) {
					if (elements != null && !elements) {
						flag = false;
						break;
					}
				}
				if (flag) {
					searchResult.add(aircraft);
				}
			}
		}
		return searchResult;
	}

	@Override
	public String toString() {
		if (aircrafts.isEmpty()) {
			return "AirCarrier is EMPTY";
		}
		StringBuilder builder = new StringBuilder("AirCarrier{");
		for (Aircraft aircraft : aircrafts) {
			if (aircraft != null) {
				builder.append("\n").append(aircraft);
			}
		}
		builder.append("\n}");
		return builder.toString();
	}

	private int parsInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
