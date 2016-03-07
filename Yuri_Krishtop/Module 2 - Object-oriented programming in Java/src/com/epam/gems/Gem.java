package com.epam.gems;

public abstract class Gem implements Comparable, Cloneable {
	private double caratWeight;
	private String cut;
	private String clarityIndex;

	public Gem(double caratWeight, String cut, String clarityIndex) {
		this.caratWeight = caratWeight;
		this.cut = cut;
		this.clarityIndex = clarityIndex;
	}

	public abstract double getPrice();

	public Gem() {
	}

	public double getCaratWeight() {
		return this.caratWeight;
	}
	
	public void setCaratWeight(double caratWeight) {
		this.caratWeight = caratWeight;
	}

	public int compareTo(Object other) {
		Gem tmp = (Gem) other;
		return (int) (this.getCaratWeight() - tmp.getCaratWeight());
	}
	
	
	public Gem clone() throws CloneNotSupportedException {
		return  (Gem) super.clone();
	}

	/**
	 * Selects multiplier for price calculation depending on clarity of gem.
	 * This selection is based on international scale of Gemological Institute
	 * of America. According to this scale: FL -> Flawless; IF -> Internally
	 * Flawless; VVS1 and VVS2 -> Very Very Small Inclusions; VS1 and VS2 ->
	 * Very Small Inclusions; SI1 and SI2 -> Small Inclusions; I1, I2, and I3 ->
	 * Imperfect.
	 * 
	 * @see http://www.gia.edu
	 * @return clarity multiplier for price calculation
	 */
	protected int getClarityMultiplier() {
		switch (clarityIndex) {
		case "FL":
			return 25;
		case "IF":
			return 20;
		case "VVS1":
			return 15;
		case "VVS2":
			return 14;
		case "VS1":
			return 10;
		case "VS2":
			return 9;
		case "SI1":
			return 7;
		case "SI2":
			return 6;
		case "I1":
			return 4;
		case "I2":
			return 3;
		case "I3":
			return 2;
		default:
			return 1;
		}
	}

	public String toString() {
		return getClass().getName() + ": " + "Weight: " + this.caratWeight + " carat" + " Price: " + this.getPrice()
				+ " $" + " Cut: " + this.cut + " " + "Clarity: " + this.clarityIndex + " ";
	}

}
