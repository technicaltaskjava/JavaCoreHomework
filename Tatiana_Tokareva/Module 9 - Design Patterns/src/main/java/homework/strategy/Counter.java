package homework.strategy;

class Counter {
	private Calculator calculator;


	void setCalculator(final Calculator calculator) {
		this.calculator = calculator;
	}

	int result(int number1, int number2) {
		return calculator.count(number1, number2);
	}


}
