package javase.t01.tours;

import javase.t01.basetour.BaseTour;
import javase.t01.basetour.Food;
import javase.t01.basetour.Transport;

/**
 * Class of sea tour
 * Created by Yury Vislobodsky on 30.03.2016.
 * 
 */
public class SeaTour extends BaseTour {
	private Transport transport;
	private Food food;
	private int daysNumber;

	public SeaTour(Transport transport, Food food, int daysNumber) {
		setTransport(transport);
		setFood(food);
		setDaysNumber(daysNumber);
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public void setDaysNumber(int daysNumber) {
		this.daysNumber = daysNumber;
	}

	@Override
	public int getDaysNumber() {
		return daysNumber;
	}

	@Override
	public double getCost() {
		return transport.getCost() + food.getCost(getDaysNumber());
	}

	@Override
	public String description() {
		return "Sea tour (" + getTransport() + ", " + getFood() + ", days:" + getDaysNumber() + ")";
	}
}
