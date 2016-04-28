package homework;

import homework.builder.Order;
import homework.factory.StoveSimulation;
import homework.singleton.Logger;
import homework.strategy.CalculatorSimulation;

import java.util.Scanner;

public class Menu {
	private Menu() {
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String menu = getMenu();
		boolean start = true;
		while (start) {
			System.out.println(menu);
			String input = scanner.nextLine();
			switch (input) {
				case "1":
					order();
					break;
				case "2":
					simulateStoveWork();
					break;
				case "3":
					logger();
					break;
				case "4":
					simulateCalculator();
					break;
				case "5":
					start = false;
					scanner.close();
					break;
				default:
					System.out.println("Enter incorrect menu item\nExpected from 1 to 5");
			}
		}
	}

	private static void simulateCalculator() {
		CalculatorSimulation calculator = new CalculatorSimulation();
		calculator.showMenu();
	}

	private static void simulateStoveWork() {
		StoveSimulation stoveSimulation = new StoveSimulation();
		stoveSimulation.stoveWork();
	}

	private static void logger() {
		Logger logger = Logger.getInstance();
		logger.message("message");
		System.out.println(logger.print());
	}

	private static void order() {
		Order order = new Order.Builder()
				.coffeeBuild(2)
				.hamburgerBuild(1)
				.nuggetsBuild(2)
				.crispyBuild(3)
				.build();
		System.out.println(order);
	}

	private static String getMenu() {
		StringBuilder builder = new StringBuilder("Menu items:").append("\n")
				.append("1 - builder").append("\n")
				.append("2 - factory ").append("\n")
				.append("3 - singleton").append("\n")
				.append("4 - strategy").append("\n")
				.append("5 - exit").append("\n")
				.append("Select operations:");
		return builder.toString();
	}


}




