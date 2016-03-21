package javase02.carriages;

import javase02.basecarriage.BaseCarriage;

/**
 * Class of second-class carriage
 * @author YURY.VISLOBODSKY
 * 
 */

public class SecondClassCarriage extends BaseCarriage {	
	public final static int BAGGAGE_WEIGHT_IN_KG_PER_PASSENGER = 20;
	private int passengersNumber;
	
	public SecondClassCarriage(int passengersNumber) {
		setPassengersNumber(passengersNumber);
	}
	
	public void setPassengersNumber(int passengersNumber) {
		this.passengersNumber = (passengersNumber > 0) ? passengersNumber : 0;	
	}
	
	@Override
	public int getPassengersNumber() {
		return passengersNumber;
	}
	
	@Override
	public int getBaggageWeightInKg() {
		return getPassengersNumber() * BAGGAGE_WEIGHT_IN_KG_PER_PASSENGER;
	}
	
	@Override
	public String description() {
		return "Second-class car. (" + getPassengersNumber() + " passengers)";			
	}
}
