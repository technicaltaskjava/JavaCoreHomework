package javase.t01.tours;

import javase.t01.basetour.BaseTour;
import javase.t01.basetour.Transport;

/**
 * Class of shopping tour
 * Created by Yury Vislobodsky on 30.03.2016.
 * 
 */
public class Shopping extends BaseTour {
	public static final int DAYS_NUMBER = 2;
	private Transport transport;

	public Shopping(Transport transport) {
		setTransport(transport);
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	@Override
	public int getDaysNumber() {
		return DAYS_NUMBER;
	}

	@Override
	public double getCost() {
		return transport.getCost();
	}

	@Override
	public String description() {
		return "Shopping (" + getTransport() + ")";
	}
}
