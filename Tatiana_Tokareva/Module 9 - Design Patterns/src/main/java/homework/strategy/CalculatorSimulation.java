package homework.strategy;

import java.util.Scanner;

public class CalculatorSimulation {

	public static final String RESULT = "result=%s";

	private static String getMenu() {
		StringBuilder builder = new StringBuilder("Menu items:").append("\n")
				.append("1 - sum").append("\n")
				.append("2 - subtraction ").append("\n")
				.append("3 - multiplication").append("\n")
				.append("4 - exit").append("\n")
				.append("Select operations:");
		return builder.toString();
	}

	private static int getNumber(final Scanner scanner, final Object message) {
		while (true) {
			System.out.println(String.format("Enter %s number", message));
			String line = scanner.nextLine();
			try {
				return Integer.parseInt(line);
			} catch (NumberFormatException e) {
				System.out.println("Please enter correct, enter number?");
			}
		}
	}

	public void showMenu() {
		Scanner scanner = new Scanner(System.in);
		int number1 = getNumber(scanner, "first");
		int number2 = getNumber(scanner, "second");
		Counter counter = new Counter();
		String menu = getMenu();
		boolean start = true;
		while (start) {
			System.out.println(menu);
			String input = scanner.nextLine();
			switch (input) {
				case "1":
					counter.setCalculator(new Sum());
					System.out.println(String.format(RESULT, counter.result(number1, number2)));
					break;
				case "2":
					counter.setCalculator(new Subtraction());
					System.out.println(String.format(RESULT, counter.result(number1, number2)));
					break;
				case "3":
					counter.setCalculator(new Multiplication());
					System.out.println(String.format(RESULT, counter.result(number1, number2)));
					break;
				case "4":
					start = false;
					scanner.close();
					break;
				default:
					System.out.println("Enter incorrect menu item\nExpected from 1 to 4");
			}
		}
	}
}
