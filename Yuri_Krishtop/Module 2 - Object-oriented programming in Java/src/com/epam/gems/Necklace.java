package com.epam.gems;

import java.util.Arrays;

public class Necklace implements Cloneable {
	private Gem[] necklace;
	private Gem[] selectedGems;

	public Necklace() {
	}

	public Necklace(Gem... gems) {
		this.necklace = new Gem[gems.length];
		for (int i = 0; i < gems.length; i++) {
			this.necklace[i] = gems[i];
		}
	}

	public double getNecklaceCaratWeight() {
		double caratWeight = 0;
		for (int i = 0; i < this.necklace.length; i++) {
			caratWeight += this.necklace[i].getCaratWeight();
		}
		return caratWeight;
	}

	public double getPrice() {
		double caratPrice = 0;
		for (int i = 0; i < this.necklace.length; i++) {
			caratPrice += this.necklace[i].getPrice();
		}
		return caratPrice;
	}

	public Necklace clone() throws CloneNotSupportedException {
		return (Necklace) super.clone();
	}

	public Necklace getByPriceRange(double minPrice, double maxPrice) throws CloneNotSupportedException {
		if (this.selectedGems != null) {
			this.selectedGems = null;
		}
		if (minPrice < 0) {
			minPrice = -minPrice;
		}
		if (maxPrice < 0) {
			maxPrice = -maxPrice;
		}
		if (minPrice > maxPrice) {
			double tmp = minPrice;
			minPrice = maxPrice;
			maxPrice = tmp;
		}
		for (int i = 0; i < this.necklace.length; i++) {
			double thisPrice = this.necklace[i].getPrice();
			if ((thisPrice >= minPrice) && (thisPrice <= maxPrice)) {
				if (this.selectedGems == null) {
					this.selectedGems = new Gem[] { this.necklace[i] };
				} else {
					Gem[] newArr;
					newArr = Arrays.copyOf(this.selectedGems, this.selectedGems.length + 1);
					newArr[newArr.length - 1] = this.necklace[i];
					this.selectedGems = Arrays.copyOf(newArr, newArr.length);
				}
			}
		}
		Gem[] arrGems = new Gem[this.selectedGems.length];
		for (int i = 0; i < this.selectedGems.length; i++) {
			arrGems[i] = this.selectedGems[i].clone();
		}
		this.selectedGems = null;
		return new Necklace(arrGems);
	}

	public void showInfo() {
		for (int i = 0; i < this.necklace.length; i++) {
			System.out.println(this.necklace[i]);
		}
	}

}
