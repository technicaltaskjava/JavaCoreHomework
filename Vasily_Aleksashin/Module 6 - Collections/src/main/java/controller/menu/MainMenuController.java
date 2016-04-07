package controller.menu;

import controller.MainController;
import exception.ExitException;
import exception.FileIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuController {
	static final String SEPARATOR = "\n============================================";
	static final String SOMETHING_HAPPENED = "Something happened that should not have happened";
	private static final Logger logger = LoggerFactory.getLogger(MainMenuController.class);

	public void show(final MainController controller) throws ExitException {
		while (true) {
			StringBuilder builder = new StringBuilder(SEPARATOR);
			builder.append("\n\tMAIN MENU")
					.append(SEPARATOR)
					.append("\n[0] Run task #1 - AirCarrier\n" +
							"[1] Run task #2 - Custom List implementation\n" +
							"[2] Run task #3 - Store Number\n" +
							"[3] Run task #4 - Parking\n" +
							"[4] Run task #5 - Show description of Interface\n" +
							"[5] Exit")
					.append(SEPARATOR)
					.append("\nEnter menu item:");
			controller.print(builder.toString());
			String input = controller.read();
			switch (input) {
				case "0":
					startAirCarrier(controller);
					break;
				case "1":
					controller.print("See the CustomListTest class");
					break;
				case "2":
					startStore(controller);
					break;
				case "3":
					startParking(controller);
					break;
				case "4":
					showDescription(controller);
					break;
				case "5":
					throw new ExitException();
				default:
					controller.print(String.format("%nEntered menu item '%s' incorrect, expected 0 - 5", input));
			}
		}
	}

	private void startAirCarrier(final MainController controller) {
		AirCarrierMenuController airCarrierMenuController = new AirCarrierMenuController();
		airCarrierMenuController.show(controller);
	}
	
	private void startStore(final MainController controller) {
		StoreMenuController storeMenuController = new StoreMenuController();
		storeMenuController.show(controller);
	}
	
	private void startParking(final MainController controller) {
		ParkingMenuController parkingMenuController = new ParkingMenuController();
		parkingMenuController.show(controller);
	}

	private void showDescription(final MainController controller) {
		DescriptionController descriptionController = new DescriptionController();
		try {
			String description = descriptionController.get();
			controller.print(String.format("%n%s", description));
		} catch (FileIOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
