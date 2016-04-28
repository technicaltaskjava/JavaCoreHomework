package homework.factory;

public class ElectricStove implements Stove {
	private boolean state;
	private long time;
	private int temperature;


	@Override
	public void start(final boolean state) {
		this.state = state;
	}

	@Override
	public void setTimer(final long time) {
		this.time = time;
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public int getTemperature() {
		return temperature;
	}

	@Override
	public void setTemperature(final int temperature) {
		if (isState()) {
			this.temperature = temperature;

		} else
			System.out.println("please set on the stove");
	}

	@Override
	public double getExpense() {
		return 2.0 * time / 60 * 0.45;
	}

	@Override
	public void stop() {
		state = false;
		System.out.println(String.format("%s is done", getClass().getSimpleName()));

	}

	public boolean isState() {
		return state;
	}
}
