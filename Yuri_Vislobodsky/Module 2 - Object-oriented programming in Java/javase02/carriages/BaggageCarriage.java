package javase02.carriages;

import javase02.basecarriage.BaseCarriage;

/**
 * Class of baggage carriage
 * @author YURY.VISLOBODSKY
 * 
 */

public class BaggageCarriage extends BaseCarriage {
	private int baggageWeightInKg;
	
	public BaggageCarriage(int baggageWeightInKg) {
		this.baggageWeightInKg = (baggageWeightInKg > 0) ? baggageWeightInKg : 0;	
	}
	
	@Override
	public int getPassengersNumber() {
		return 0;
	}
	
	@Override
	public int getBaggageWeightInKg() {
		return baggageWeightInKg;
	}	
	
	@Override
	public String description() {
		return "Baggage car. (" + getBaggageWeightInKg() + " kg)";			
	}
}
