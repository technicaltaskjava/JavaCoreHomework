package controller.menu;

import controller.MainController;
import exception.GetInstanceException;
import exception.ParameterValidateException;
import model.task4.Parking;
import model.task4.entity.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class ParkingMenuController {
	private static final Logger logger = LoggerFactory.getLogger(ParkingMenuController.class);
	private final List<Car> cars = new ArrayList<>();
	private Parking parking = null;

	void show(final MainController mainController) {
		boolean flag = true;
		try {
			initParking(mainController);
		} catch (GetInstanceException e) {
			logger.error(e.getMessage(), e);
			flag = false;
		}
		while (flag) {
			StringBuilder builder = new StringBuilder(MainMenuController.SEPARATOR);
			builder.append("\n\tPARKING MENU")
					.append(MainMenuController.SEPARATOR)
					.append("\n[0] Show parking\n" +
							"[1] Put the car into the parking lot\n" +
							"[2] Pick up the car from the parking lot\n" +
							"[3] The amount of free parking places\n" +
							"[4] Find the car in the parking lot\n" +
							"[5] Change the place car in the parking lot\n" +
							"[6] Exit")
					.append(MainMenuController.SEPARATOR)
					.append("\nEnter menu item:");
			mainController.print(builder.toString());
			String input = mainController.read();
			switch (input) {
				case "0":
					mainController.print(String.format("%nParking%n%s", parking.getParking()));
					break;
				case "1":
					putCar(mainController);
					break;
				case "2":
					pickCar(mainController);
					break;
				case "3":
					mainController.print(String.format("Free parking places: %s", parking.freePlacesCount()));
					break;
				case "4":
					findCar(mainController);
					break;
				case "5":
					changePlace(mainController);
					break;
				case "6":
					flag = false;
					break;
				default:
					mainController.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 6", input));
			}
		}
	}
	
	private void changePlace(final MainController mainController) {
		Car car = getCar(mainController);
		int newPlace;
		while (true) {
			mainController.print(String.format("Enter number of new place for car %s", car));
			final String input = mainController.read();
			try {
				newPlace = Integer.parseInt(input);
				final boolean swap = parking.changePlace(car, newPlace);
				if (!swap) {
					mainController.print(String.format("Place '%s' is not empty", newPlace));
				} else {
					break;
				}
			} catch (NumberFormatException e) {
				mainController.print(String.format("Expected a number, but entered '%s'%nTry again", e.getMessage()));
			} catch (ParameterValidateException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	private void findCar(final MainController mainController) {
		Car car = getCar(mainController);
		try {
			final int placeNumber = parking.getPlaceNumber(car);
			if (placeNumber < 0) {
				mainController.print(String.format("The car %s is not yet parked", car));
			} else {
				mainController.print(String.format("The car %s is on the %s place", car, placeNumber));
			}
		} catch (ParameterValidateException e) {
			logger.error(MainMenuController.SOMETHING_HAPPENED, e);
		}
	}
	
	private void pickCar(final MainController mainController) {
		Car car = getCar(mainController);
		try {
			final boolean exist = parking.leaving(0, car);
			if (!exist) {
				mainController.print(String.format("The car %s is not yet parked", car));
			}
		} catch (ParameterValidateException e) {
			logger.error(MainMenuController.SOMETHING_HAPPENED, e);
		}
	}

	private void putCar(final MainController mainController) {
		Car car = getCar(mainController);
		try {
			final boolean exist = parking.coming(0, car);
			if (!exist) {
				mainController.print(String.format("The car %s is already parked", car));
			}
		} catch (ParameterValidateException e) {
			logger.error(MainMenuController.SOMETHING_HAPPENED, e);
		}
	}

	private void initParking(final MainController mainController) throws GetInstanceException {
		mainController.print("How many parking spaces you need?");
		int count;
		while (true) {
			String placeCount = mainController.read();
			try {
				count = Integer.parseInt(placeCount);
				break;
			} catch (NumberFormatException e) {
				mainController.print(String.format("Expected a positive number, but entered '%s'%nTry again", e.getMessage()));
			}
		}
		parking = Parking.getInstance(count);
	}

	private Car getCar(final MainController mainController) {
		mainController.print("Enter the license plate for the car:");
		final String input = mainController.read();
		Car car = null;
		try {
			car = Car.getCar(input);
			cars.add(car);
		} catch (GetInstanceException e) {
			logger.error(e.getMessage(), e);
			for (Car element : cars) {
				if (element.getLicensePlate().equals(input)) {
					car = element;
					break;
				}
			}
		}
		return car;
	}
}
