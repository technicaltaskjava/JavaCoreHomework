package homework.factory;

public class StoveSimulation {
	public void stoveWork() {

		Factory factory = new FactoryStove();

		System.out.println("Gas stove work");
		Stove gasStove = factory.getGasStove();
		gasStove.start(true);
		gasStove.setTimer(65);
		long timeGasStove = gasStove.getTime();
		System.out.println("Stove working " + timeGasStove + " minute");
		gasStove.setTemperature(50);
		int temperatureGasStove = gasStove.getTemperature();
		System.out.println("Stove working at temperature " + temperatureGasStove);
		double expenseGas = gasStove.getExpense();
		System.out.println("you spent gas on " + expenseGas + "grn");
		gasStove.stop();

		System.out.println("\nElectric stove work");
		Stove electricStove = factory.getElectricStove();
		electricStove.start(true);
		electricStove.setTimer(40);
		long timeElectricStove = electricStove.getTime();
		System.out.println("Stove working " + timeElectricStove + " minute");
		electricStove.setTemperature(65);
		int temperatureElectricStove = electricStove.getTemperature();
		System.out.println("Stove working at temperature " + temperatureElectricStove);
		double expenseElectricStove = electricStove.getExpense();
		System.out.println("you spent electricity on " + expenseElectricStove + "grn");
		electricStove.stop();

	}

}
