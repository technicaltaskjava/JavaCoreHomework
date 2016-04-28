package homework.strategy;

class Sum implements Calculator {

	@Override
	public int count(final int number1, final int number2) {

		return number1 + number2;
	}
}
