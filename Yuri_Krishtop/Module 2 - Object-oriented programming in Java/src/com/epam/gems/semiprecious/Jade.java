package com.epam.gems.semiprecious;

import com.epam.gems.Gem;

public class Jade extends Gem {
	private final int basePricePerCarat = 30;

	public Jade(double caratWeight, String cut, String clarityIndex) {
		super(caratWeight, cut, clarityIndex);
	}
	
	public double getPrice() {
		return this.basePricePerCarat * this.getCaratWeight() * this.getClarityMultiplier() / 2;
	}
	
	public Jade clone() throws CloneNotSupportedException {
		return  (Jade) super.clone();
	}
}
