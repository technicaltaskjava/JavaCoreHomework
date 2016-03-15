package javase02.basecarriage;

/**
 * Base class of carriage
 * @author YURY.VISLOBODSKY
 * 
 */

public abstract class BaseCarriage {
	private boolean ranged;
	
	public BaseCarriage() {
		ranged = true;
	}
	
	public boolean isRanged() {
		return ranged;
	}
	
	public void setRanged(boolean ranged) {
		this.ranged = ranged;
	}
	
	public abstract int getPassengersNumber();
	public abstract int getBaggageWeightInKg();	
	public abstract String description();
}
