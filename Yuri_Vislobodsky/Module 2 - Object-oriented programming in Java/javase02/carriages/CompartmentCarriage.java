package javase02.carriages;

import javase02.basecarriage.BaseCarriage;

/**
 * Class of compartment carriage
 * @author YURY.VISLOBODSKY
 * 
 */

public class CompartmentCarriage extends BaseCarriage {	
	public final static int PASSENGERS_PER_COMPARTMENT = 4;
	public final static int BAGGAGE_WEIGHT_IN_KG_PER_PASSENGER = 40;
	private int compartmentsNumber;
	
	public CompartmentCarriage(int compartmentsNumber) {
		setCompartmentsNumber(compartmentsNumber);
	}
	
	public int getCompartmentsNumber() {
		return compartmentsNumber;
	}
	
	public void setCompartmentsNumber(int compartmentsNumber) {
		this.compartmentsNumber = (compartmentsNumber > 0) ? compartmentsNumber : 0;		
	}
	
	@Override
	public int getPassengersNumber() {
		return getCompartmentsNumber() * PASSENGERS_PER_COMPARTMENT;
	}
	
	@Override
	public int getBaggageWeightInKg() {
		return getPassengersNumber() * BAGGAGE_WEIGHT_IN_KG_PER_PASSENGER;
	}
	
	@Override
	public String description() {
		return "Compartment car. (" + getCompartmentsNumber() + " compartments)";			
	}
}
