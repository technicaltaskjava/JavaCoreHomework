package com.epam.gems.precious;

import com.epam.gems.Gem;

public class Sapphire extends Gem {

	private final int basePricePerCarat = 300;
	private int colorIndex;

	public Sapphire(double caratWeight, String cut, String clarityIndex, int colorIndex) {
		super(caratWeight, cut, clarityIndex);
		this.colorIndex = colorIndex;
	}

	private double getColorMultiplier() {
		while (this.colorIndex > 5) {
			this.colorIndex /= 5;
		}
		return (double) (11 - 2 * this.colorIndex) / 5;
	}
	
	public Sapphire clone() throws CloneNotSupportedException {
		return  (Sapphire) super.clone();
	}

	public double getPrice() {
		int weightIndex;
		if (this.getCaratWeight() < 1) {
			weightIndex = 1;
		} else {
			weightIndex = (int) getCaratWeight() * (int) getCaratWeight();
		}
		return this.basePricePerCarat * this.getCaratWeight() * this.getClarityMultiplier()
				* this.getColorMultiplier() * weightIndex;
	}
	
	public String toString() {
		return super.toString()+" Color index: "+this.colorIndex; 
	}
}