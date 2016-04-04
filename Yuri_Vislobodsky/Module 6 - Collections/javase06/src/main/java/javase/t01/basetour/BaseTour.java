package javase.t01.basetour;

import java.io.Serializable;

/**
 * Base class of tour
 * Created by Yury Vislobodsky on 30.03.2016.
 * 
 */
public abstract class BaseTour implements Serializable {
	private boolean ranged;
	
	public BaseTour() {
		ranged = true;
	}
	
	public boolean isRanged() {
		return ranged;
	}
	
	public void setRanged(boolean ranged) {
		this.ranged = ranged;
	}
	
	public abstract int getDaysNumber();
	public abstract double getCost();
	public abstract String description();
}
