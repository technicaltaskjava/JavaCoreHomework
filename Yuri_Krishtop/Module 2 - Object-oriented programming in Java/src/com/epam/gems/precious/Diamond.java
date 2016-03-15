package com.epam.gems.precious;

import com.epam.gems.Gem;

public class Diamond extends Gem {

	private final int basePricePerCarat = 400;
	private char colorIndex;
	
	public Diamond(double caratWeight, String cut, String clarityIndex, char colorIndex) {
		super(caratWeight, cut, clarityIndex);
		this.colorIndex = colorIndex;
	}

	/**
	 * Selects multiplier for price calculation depending on color of diamond.
	 * This selection is based on international scale of Gemological Institute
	 * of America. According to this scale D – colorless diamond ... Z – yellow
	 * diamond.
	 * 
	 * @see http://www.gia.edu
	 * @return color multiplier for price calculation
	 */
	private double getColorMultiplier() {
		double ColorMultiplier = 1;
		for (char c = 'D'; c <= 'Z'; c++) {
			if (this.colorIndex == c) {
				ColorMultiplier = 91 - ((int) c) ;
			}
		}
		return ColorMultiplier/10;
	}
	
	public Diamond clone() throws CloneNotSupportedException {
		return  (Diamond) super.clone();
	}

	public double getPrice() {
		int weightIndex;
		if (this.getCaratWeight() < 0.29) {
			weightIndex = 1;
		} else if (this.getCaratWeight() < 0.99) {
			weightIndex = 3;
		} else {
			weightIndex = 10;
		}
		return this.basePricePerCarat * this.getCaratWeight() * this.getClarityMultiplier() * this.getColorMultiplier()
				* weightIndex;
	}
	

	
	public String toString() {
		return super.toString()+" Color index: "+this.colorIndex; 
	}

}
