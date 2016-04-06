package javase.t01.demo;

import javase.t01.basetour.Food;
import javase.t01.basetour.Transport;
import javase.t01.travel.Travel;
import javase.t01.basetour.BaseTour;
import javase.t01.tours.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Scanner;

/**
 * Class of Travel object demo console interface
 * Created by Yury Vislobodsky on 30.03.2016.
 * 
 */
public class TravelDemo {
	private static Logger logger = LoggerFactory.getLogger(TravelDemo.class);
	private Travel myTravel;
	private Scanner in;
	private static final String CONFIG_FILE_NAME = "src/main/resources/config.dat";
	
	public TravelDemo() {
		myTravel = new Travel();
		in = new Scanner(System.in);
	}
			
	public void doAddSeaTour() {
		int daysNumber;
		Transport transport;
		Food food;
		if (((daysNumber = inputNumber("Input a number of days : ")) < 0) ||
				((transport = inputTransport()) == null) ||
				((food = inputFood()) == null)) {
			logger.info("Invalid sea tour input!");
		} else {
			myTravel.addTour(new SeaTour(transport, food, daysNumber));
		}
	}

	public void doAddExcursion() {
		Transport transport;
		Food food;
		if (((transport = inputTransport()) == null) ||
				((food = inputFood()) == null)) {
			logger.info("Invalid excursion input!");
		} else {
			myTravel.addTour(new Excursion(transport, food));
		}
	}

	public void doAddShopping() {
		Transport transport;
		if ((transport = inputTransport()) == null) {
			logger.info("Invalid shopping input!");
		} else {
			myTravel.addTour(new Shopping(transport));
		}
	}	
	
	public void doDeleteTour() {
		if (myTravel.length() > 0) {
			myTravel.deleteTour();
			logger.info("Last tour is deleted.");
		} else {
			logger.info("Nothing to delete!");
		}
	}
	
	public void doShowTotals() {
		logger.info(String.format("Total number of days : %d%n",
							myTravel.getDaysNumberTotal()));
		logger.info(String.format("Total cost : %6.2f%n",
							myTravel.getCostTotal()));
	}
	
	public void doSortByDaysNumber() {
		if (myTravel.length() > 0) {
			myTravel.sortByDaysNumber();
			logger.info("Tours are sorted by number of days.");
		} else {
			logger.info("Nothing to sort!");
		}		
	}

	public void doSortByCost() {
		if (myTravel.length() > 0) {
			myTravel.sortByCost();
			logger.info("Tours are sorted by cost.");
		} else {
			logger.info("Nothing to sort!");
		}		
	}

	public void doRangeOnDaysNumber() {
		if (myTravel.length() > 0) {
			logger.info("Input a min number of days : ");
			int minNumber = in.nextInt();
			logger.info("Input a max number of days : ");
			int maxNumber = in.nextInt();	
			myTravel.rangeOnDaysNumber(minNumber, maxNumber);
			logger.info("Tours are ranged on number of days.");
		} else {
			logger.info("Nothing to range!");
		}		
	}	

	public void doRangeOnCost() {
		if (myTravel.length() > 0) {
			logger.info("Input a min cost : ");
			double minCost = in.nextDouble();
			logger.info("Input a max cost : ");
			double maxCost = in.nextDouble();
			myTravel.rangeOnCost(minCost, maxCost);
			logger.info("Tours are ranged on cost.");
		} else {
			logger.info("Nothing to range!");
		}		
	}		

	public void doRangeOff() {
		if (myTravel.length() > 0) {
			myTravel.rangeOff();
		}		
	}

	public int inputNumber(String message) {
		logger.info(message);
		return in.nextInt();
	}

	public Transport inputTransport() {
		logger.info("Input a transport ");
		for (Transport transport : Transport.values()) {
			logger.info(transport.ordinal() + "-" + transport.name() + " ");
		}
		logger.info(" : ");
		int input = in.nextInt();
		if ((input >= 0) && (input < Transport.values().length)) {
			return Transport.values()[input];
		} else {
			return null;
		}
	}

	public Food inputFood() {
		logger.info("Input a food ");
		for (Food food : Food.values()) {
			logger.info(food.ordinal() + "-" + food.name() + " ");
		}
		logger.info(" : ");
		int input = in.nextInt();
		if ((input >= 0) && (input < Food.values().length)) {
			return Food.values()[input];
		} else {
			return null;
		}
	}

	public void printTravelList() {
		logger.info("");
		logger.info("-----------------------------------------------------------------");
		logger.info("Tour# Description of tour                        Days        Cost");
		for (int index = 0; index < myTravel.length(); index++) {
			BaseTour tour = myTravel.getTourByIndex(index);
			if (tour.isRanged()) {
				logger.info(String.format("%3d   %-40s  %4d       %6.2f",
					index+1,
					tour.description(),
					tour.getDaysNumber(),
					tour.getCost()));
			}
		}
		logger.info("-----------------------------------------------------------------");
	}	
	
	public int menuSelect() {
		logger.info("Menu:");
		logger.info("1 - Add sea tour");
		logger.info("2 - Add excursion");
		logger.info("3 - Add shopping");
		logger.info("4 - Delete last tour");
		logger.info("5 - Show totals");
		logger.info("6 - Sort tours by number of days");
		logger.info("7 - Sort tours by cost");
		logger.info("8 - Range tours on number of days");
		logger.info("9 - Range tours on cost");
		logger.info("0 - Quit");
		logger.info("Your choice : ");
		return in.nextInt();
	}

	public void doLoadFromFile(String fileName) {
		try {
			myTravel.loadFromFile(fileName);
		} catch (Exception e) {
			logger.error("File " + fileName + " is not loaded", e);
		}
	}

	public void doSaveToFile(String fileName) {
		try {
			myTravel.saveToFile(fileName);
		} catch (Exception e) {
			logger.error("File " + fileName + " is not saved", e);
		}
	}

	public void doAction(int selectedItem) {
		switch (selectedItem) {
			case 1: doAddSeaTour();
				break;
			case 2: doAddExcursion();
				break;
			case 3: doAddShopping();
				break;
			case 4: doDeleteTour();
				break;
			case 5: doShowTotals();
				break;
			case 6: doSortByDaysNumber();
				break;
			case 7: doSortByCost();
				break;
			case 8: doRangeOnDaysNumber();
				break;
			case 9: doRangeOnCost();
				break;
			default:
		}
	}

	public void doMain() {
		int selectedItem;
		do {
			printTravelList();
			doRangeOff();
			selectedItem = menuSelect();
			doAction(selectedItem);
		} while (selectedItem!=0);

	}

	public static void main(String[] args) throws Exception {
		TravelDemo travelDemo = new TravelDemo();
		travelDemo.doLoadFromFile(CONFIG_FILE_NAME);
		travelDemo.doMain();
		travelDemo.doSaveToFile(CONFIG_FILE_NAME);
	}

}
