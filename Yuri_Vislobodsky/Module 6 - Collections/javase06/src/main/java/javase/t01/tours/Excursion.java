package javase.t01.tours;

import javase.t01.basetour.BaseTour;
import javase.t01.basetour.Food;
import javase.t01.basetour.Transport;

/**
 * Class of excursion tour
 * Created by Yury Vislobodsky on 30.03.2016.
 * 
 */
public class Excursion extends BaseTour {
	public static final double EXCURSION_COST = 30;
	public static final int DAYS_NUMBER = 3;
	private Transport transport;
	private Food food;

	public Excursion(Transport transport, Food food) {
		setTransport(transport);
		setFood(food);
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

	@Override
	public int getDaysNumber() {
		return DAYS_NUMBER;
	}
	
	@Override
	public double getCost() {
		return transport.getCost() + food.getCost(getDaysNumber()) + EXCURSION_COST;
	}
	
	@Override
	public String description() {
		return "Excursion (" + getTransport() + ", " + getFood() + ")";
	}
}
