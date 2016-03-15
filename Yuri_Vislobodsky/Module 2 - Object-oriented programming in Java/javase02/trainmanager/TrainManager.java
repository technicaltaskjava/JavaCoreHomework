package javase02.trainmanager;

import javase02.train.Train;
import javase02.basecarriage.BaseCarriage;
import javase02.carriages.*;
import javase02.trainconfig.TrainFileConfig;
import java.util.Scanner;

/**
 * Class of Train object console interface  
 * @author YURY.VISLOBODSKY
 * 
 */

public class TrainManager {
	private Train myTrain;
	private Scanner in;
	private final static String CONFIG_FILE_NAME = "config.dat";
	
	public TrainManager() {
		myTrain = new Train();
		in = new Scanner(System.in);
	}
			
	public void doAddCompartmentCarriage() {
		System.out.print("Input a number of compartments : ");
		int numberOfCompartments = in.nextInt();
		if (numberOfCompartments > 0) {
			myTrain.addCarriage(new CompartmentCarriage(numberOfCompartments));
			System.out.print("New compartment carriage is added.");
		} else {
			System.out.print("Invalid number of compartments!");
		}
	}

	public void doAddSecondClassCarriage() {
		System.out.print("Input a number of passengers : ");
		int numberOfPassengers = in.nextInt();
		if (numberOfPassengers > 0) {
			myTrain.addCarriage(new SecondClassCarriage(numberOfPassengers));
			System.out.print("New second-class carriage is added.");
		} else {
			System.out.print("Invalid number of passengers!");
		}
	}	

	public void doAddBaggageCarriage() {
		System.out.print("Input a baggage weight in kg : ");
		int weight = in.nextInt();
		if (weight > 0) {
			myTrain.addCarriage(new BaggageCarriage(weight));
			System.out.print("New baggage carriage is added.");
		} else {
			System.out.print("Invalid weight!");
		}
	}	
	
	public void doDeleteCarriage() {
		if (myTrain.length() > 0) {
			myTrain.deleteCarriage();
			System.out.print("Last carriage is deleted.");
		} else {
			System.out.print("Nothing to delete!");
		}
	}
	
	public void doShowTotals() {
		System.out.format("Total number of passengers : %d%n", 
							myTrain.getPassengersNumberTotal());
		System.out.format("Total weight of baggage (kg) : %d%n", 
							myTrain.getBaggageWeightInKgTotal());
	}
	
	public void doSortByPassengers() {
		if (myTrain.length() > 0) {
			myTrain.sortByPassengers();
			System.out.print("Carriages are sorted by number of passengers.");
		} else {
			System.out.print("Nothing to sort!");
		}		
	}

	public void doSortByWeight() {
		if (myTrain.length() > 0) {
			myTrain.sortByWeight();
			System.out.print("Carriages are sorted by baggage weight (kg).");
		} else {
			System.out.print("Nothing to sort!");
		}		
	}

	public void doRangeOnPassengers() {
		if (myTrain.length() > 0) {
			System.out.print("Input a mininum number of passengers : ");
			int minNumber = in.nextInt();
			System.out.print("Input a maximum number of passengers : ");
			int maxNumber = in.nextInt();	
			myTrain.rangeOnPassengers(minNumber, maxNumber);
			System.out.print("Carriages are ranged on number of passengers.");
		} else {
			System.out.print("Nothing to range!");
		}		
	}	

	public void doRangeOnWeight() {
		if (myTrain.length() > 0) {
			System.out.print("Input a mininum weight of baggage (kg) : ");
			int minWeight = in.nextInt();
			System.out.print("Input a maximum weight of baggage (kg) : ");
			int maxWeight = in.nextInt();	
			myTrain.rangeOnWeight(minWeight, maxWeight);
			System.out.print("Carriages are ranged on weight of baggage.");
		} else {
			System.out.print("Nothing to range!");
		}		
	}		

	public void doRangeOff() {
		if (myTrain.length() > 0) {
			myTrain.rangeOff();
		}		
	}			
	
	public void printTrainList() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Car.# Description of carriage             Passengers   Weight(kg)");
		for (int index = 0; index < myTrain.length(); index++) {
			BaseCarriage carriage = myTrain.getCarriageByIndex(index);
			if (carriage.isRanged()) {
				System.out.format("%3d   %-40s  %4d       %5d%n", 
					index+1,
					carriage.description(),
					carriage.getPassengersNumber(),
					carriage.getBaggageWeightInKg());				
			}
		}
		System.out.println("-----------------------------------------------------------------");
	}	
	
	public int menuSelect() {
		System.out.println("Menu:");
		System.out.println("1 - Add compartment carriage");
		System.out.println("2 - Add second-class carriage");
		System.out.println("3 - Add baggage carriage");
		System.out.println("4 - Delete last carriage");
		System.out.println("5 - Show total number of passengers and baggage");			
		System.out.println("6 - Sort carriages by number of passengers");
		System.out.println("7 - Sort carriages by number of baggage");
		System.out.println("8 - Range carriages on number of passengers");
		System.out.println("9 - Range carriages on weight of baggage");
		System.out.println("0 - Quit");
		System.out.print("Your choice : ");
		return in.nextInt();
	}
	
	public static void main(String[] args) throws Exception {
		TrainManager trainManager = new TrainManager();
		int selectedItem;
		
		TrainFileConfig.loadTrainFromFile(trainManager.myTrain, CONFIG_FILE_NAME);
		do {
			trainManager.printTrainList();
			trainManager.doRangeOff();
			selectedItem = trainManager.menuSelect();
			switch (selectedItem) {
				case 1: trainManager.doAddCompartmentCarriage();
						break;
				case 2: trainManager.doAddSecondClassCarriage();
						break;
				case 3: trainManager.doAddBaggageCarriage();
						break;
				case 4: trainManager.doDeleteCarriage();
						break;
				case 5: trainManager.doShowTotals();
						break;
				case 6: trainManager.doSortByPassengers();
						break;
				case 7: trainManager.doSortByWeight();
						break;						
				case 8: trainManager.doRangeOnPassengers();
						break;
				case 9: trainManager.doRangeOnWeight();
						break;											
			}
		} while (selectedItem!=0);
		TrainFileConfig.saveTrainToFile(trainManager.myTrain, CONFIG_FILE_NAME);
	}

}
