package homework.factory;

public class GasStove implements Stove {
	private boolean state;
	private long time;
	private int temperature;

	@Override
	public void start(final boolean state) {
		this.state = state;
	}

	@Override
	public void setTimer(long time) {
		this.time = time;
	}

	public long getTime() {
		return time;
	}

	@Override
	public int getTemperature() {
		return temperature;
	}

	@Override
	public void setTemperature(int temperature) {
		if (isState()) {
			this.temperature = temperature;

		} else
			System.out.println("please set on the stove");
	}

	@Override
	public double getExpense() {
		return 1.8 * time / 60 * 0.64;
	}

	public void stop() {
		state = false;
		System.out.println(String.format("%s is done", getClass().getSimpleName()));

	}

	public boolean isState() {
		return state;
	}
}
