package com.epam.gems.semiprecious;

import com.epam.gems.Gem;

public class Amber extends Gem {
	
	private final int basePricePerCarat = 10;
	
	public Amber(double caratWeight, String cut, String clarityIndex) {
		super(caratWeight, cut, clarityIndex);
	}
	
	public Amber clone() throws CloneNotSupportedException {
		return  (Amber) super.clone();
	}
	
	public double getPrice() {
		int weightIndex;
		if (this.getCaratWeight() < 25) {
			weightIndex = 1;
		} else if (this.getCaratWeight() < 50) {
			weightIndex = 2;
		} else {
			weightIndex = 3;
		}
		return this.basePricePerCarat * this.getCaratWeight() * this.getClarityMultiplier() * weightIndex;
	}
}
