package homework.factory;

public class FactoryStove implements Factory {


	@Override
	public Stove getElectricStove() {
		return new ElectricStove();
	}

	@Override
	public Stove getGasStove() {
		return new GasStove();
	}
}
