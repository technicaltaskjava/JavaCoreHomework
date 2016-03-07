package javase02.train;

import javase02.basecarriage.*;
import java.util.Arrays;

/**
 * Base class for train (array of carriages) 
 * @author YURY.VISLOBODSKY
 * 
 */

public class Train {
	private BaseCarriage[] train;
	
	public int length() {
		return (train == null) ? 0 : train.length;
	}
	
	public BaseCarriage getCarriageByIndex(int index) {
		if (index >= 0 && index < length()) {
			return train[index];
		} else {
			return null;
		}
	}
	
	public void addCarriage(BaseCarriage newCarriage) {
		BaseCarriage[] newTrain = new BaseCarriage[length()+1];
		for (int index = 0; index < length(); index++) {
			newTrain[index] = train[index];
		}
		newTrain[length()] = newCarriage;
		train = newTrain;
	}

	public void deleteCarriage() {
		if (length() == 0) {
			return;
		}
		BaseCarriage[] newTrain = new BaseCarriage[length()-1];
		for (int index = 0; index < length()-1; index++) {
			newTrain[index] = train[index];
		}
		train = newTrain;
	}
	
	public void deleteAll() {
		train = new BaseCarriage[0];
	}
	
	public int getPassengersNumberTotal() {
		int total = 0;
		for (BaseCarriage carriage : train) {
			total += carriage.getPassengersNumber();
		}		
		return total;
	}

	public int getBaggageWeightInKgTotal() {
		int total = 0;
		for (BaseCarriage carriage : train) {
			total += carriage.getBaggageWeightInKg();
		}		
		return total;
	}
	
	public void sortByPassengers() {
		Arrays.sort(train, new BaseCarriageSortedByPassengers());
	}
	
	public void sortByWeight() {
		Arrays.sort(train, new BaseCarriageSortedByWeight());
	}	
	
	public void rangeOnPassengers(int minNumber, int maxNumber) {
		for (BaseCarriage carriage : train) {
			int number = carriage.getPassengersNumber();
			carriage.setRanged(number>=minNumber && number<=maxNumber);
		}				
	}
	
	public void rangeOnWeight(int minWeight, int maxWeight) {
		for (BaseCarriage carriage : train) {
			int weight = carriage.getBaggageWeightInKg();
			carriage.setRanged(weight>=minWeight && weight<=maxWeight);
		}				
	}	
	
	public void rangeOff() {
		for (BaseCarriage carriage : train) {
			carriage.setRanged(true);
		}				
	}
}
