package controller.menu;

import controller.MainController;
import exception.FileIOException;
import exception.ParameterValidateException;
import model.task1.AirCarrier;
import model.task1.comparator.ComparatorByFlyingRange;
import model.task1.comparator.ComparatorByModel;
import model.task1.entity.Aircraft;
import model.task1.entity.Airfreighter;
import model.task1.entity.Airliner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.FileLoader;

import java.util.Arrays;
import java.util.List;

class AirCarrierMenuController {
	private static final Logger logger = LoggerFactory.getLogger(AirCarrierMenuController.class);

	private static final String EXPECTED_0_1 = "%nEntered item '%s' incorrect, expected 0 - 1";

	private final AirCarrier carrier = new AirCarrier();

	void show(final MainController mainController) {
		boolean flag = true;
		while (flag) {
			StringBuilder builder = new StringBuilder(MainMenuController.SEPARATOR);
			builder.append("\n\tAIRCARRIER MENU")
					.append(MainMenuController.SEPARATOR)
					.append("\n[0] Load aircraft from file\n" +
							"[1] Edit aircraft\n" +
							"[2] Calculate arguments\n" +
							"[3] Sort aircraft\n" +
							"[4] Find aircraft by parameters\n" +
							"[5] Show aircraft park\n" +
							"[6] Exit")
					.append(MainMenuController.SEPARATOR)
					.append("\nEnter menu item:");
			mainController.print(builder.toString());
			String input = mainController.read();
			switch (input) {
				case "0":
					loadAircraft(mainController);
					break;
				case "1":
					editAircraft(mainController);
					break;
				case "2":
					getCalculation(mainController);
					break;
				case "3":
					selectSort(mainController);
					break;
				case "4":
					findAircraft(mainController);
					break;
				case "5":
					mainController.print("\n" + carrier.toString());
					break;
				case "6":
					flag = false;
					break;
				default:
					mainController.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 6", input));
			}
		}
	}

	private void editAircraft(final MainController mainController) {
		boolean flag = true;
		while (flag) {
			mainController.print("Choose the argument to calculate");
			mainController.print("[0] Add aircraft from Console");
			mainController.print("[1] Remove aircraft by model");
			String input = mainController.read();
			switch (input) {
				case "0":
					addAircraft(mainController);
					flag = false;
					break;
				case "1":
					removeAircraft(mainController);
					flag = false;
					break;
				default:
					mainController.print(String.format(EXPECTED_0_1, input));
			}
		}
	}

	private void getCalculation(final MainController mainController) {
		boolean flag = true;
		while (flag) {
			mainController.print("Choose the argument to calculate");
			mainController.print("[0] Calculate general passenger capacity");
			mainController.print("[1] Calculate general tonnage");
			String input = mainController.read();
			switch (input) {
				case "0":
					calculateCapacity(mainController);
					flag = false;
					break;
				case "1":
					calculateTonnage(mainController);
					flag = false;
					break;
				default:
					mainController.print(String.format(EXPECTED_0_1, input));
			}
		}
	}

	private void selectSort(final MainController mainController) {
		boolean flag = true;
		while (flag) {
			mainController.print("Choose a method for sorting");
			mainController.print("[0] Sort aircraft by flaying range");
			mainController.print("[1] Sort aircraft by model");
			String input = mainController.read();
			switch (input) {
				case "0":
					sortByRange(mainController);
					flag = false;
					break;
				case "1":
					sortByModel(mainController);
					flag = false;
					break;
				default:
					mainController.print(String.format(EXPECTED_0_1, input));
			}
		}
	}

	private void findAircraft(MainController mainController) {
		mainController.print("To find a plane, enter its properties:");
		mainController.print("Model, Capacity, Tonnage, Flying Range");
		mainController.print("Example: Boeing, 500, 0, 6000");
		mainController.print("'0' means that the parameter is ignored");
		final String input = mainController.read();
		String[] split = input.split("[,][ ]?");
		if (split.length < 4) {
			split = Arrays.copyOf(split, 4);
		}
		String model = "".equals(split[0]) ? null : split[0];
		int capacity = 0;
		int tonnage = 0;
		int flyingRange = 0;
		try {
			capacity = Integer.parseInt(split[1]);
			tonnage = Integer.parseInt(split[2]);
			flyingRange = Integer.parseInt(split[3]);
		} catch (NumberFormatException e) {
			//use default value
		}
		List<Aircraft> aircrafts = carrier.find(model, capacity, tonnage, flyingRange);
		mainController.print("Result of search:");
		mainController.print(aircrafts.toString());
	}

	private void removeAircraft(final MainController mainController) {
		mainController.print("To remove a plane, enter its model:");
		final String input = mainController.read();
		Aircraft aircraft;
		try {
			aircraft = new Airliner(input);
			carrier.remove(aircraft);
		} catch (ParameterValidateException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void addAircraft(final MainController mainController) {
		mainController.print("To add a plane, enter its properties:");
		mainController.print("Model (required), Capacity, Tonnage, Flying Range");
		mainController.print("Example: Boeing, 500, 0, 6000");
		mainController.print("Missing parameters are replaced with 0");
		final String input = mainController.read();
		String[] split = input.split("[,][ ]?");
		if (split.length < 4) {
			split = Arrays.copyOf(split, 4);
		}
		String model = split[0];
		int capacity = 0;
		int tonnage = 0;
		int flyingRange = 0;
		try {
			capacity = Integer.parseInt(split[1]);
			tonnage = Integer.parseInt(split[2]);
			flyingRange = Integer.parseInt(split[3]);
		} catch (NumberFormatException e) {
			//use default value
		}
		Aircraft aircraft;
		try {
			if (capacity > 0) {
				aircraft = new Airliner(model);
			} else {
				aircraft = new Airfreighter(model);
			}
			aircraft.setCapacity(capacity);
			aircraft.setTonnage(tonnage);
			aircraft.setFlyingRange(flyingRange);
			carrier.add(aircraft);
		} catch (ParameterValidateException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void calculateTonnage(final MainController mainController) {
		final int tonnage = carrier.getTonnage();
		mainController.print(String.format("Total tonnage is '%s'", tonnage));
	}

	private void calculateCapacity(final MainController mainController) {
		final int capacity = carrier.getCapacity();
		mainController.print(String.format("Total capacity is '%s'", capacity));
	}

	private void sortByModel(final MainController mainController) {
		carrier.sort(new ComparatorByModel());
		mainController.print(carrier.toString());
	}
	
	private void sortByRange(final MainController mainController) {
		carrier.sort(new ComparatorByFlyingRange());
		mainController.print(carrier.toString());
	}
	
	private void loadAircraft(final MainController mainController) {
		mainController.print("Do you want load aircraft from default file? (y/n)");
		String input = mainController.read().toLowerCase();
		String aircraftData = null;
		try {
			if ("y".equals(input)) {
				aircraftData = FileLoader.loadFromTxt();
			} else {
				mainController.print("Enter file name to load data:");
				input = mainController.read();
				aircraftData = FileLoader.loadFromTxt(input);
			}
		} catch (FileIOException e) {
			logger.error(e.getMessage(), e);
		}
		if (aircraftData == null) {
			mainController.print("Can not load data from file");
		} else {
			final int result = carrier.load(aircraftData);
			mainController.print(String.format("Loaded successfully with %s elements", result));
		}
	}
}
