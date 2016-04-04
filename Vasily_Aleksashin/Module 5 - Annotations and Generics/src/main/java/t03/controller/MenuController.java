package t03.controller;


import t01.exception.ExitException;
import t03.model.entity.Pair;
import t03.model.entity.Triple;
import t03.model.entity.Unit;

class MenuController {

	void show(final MainController mainController) throws ExitException {
		while (true) {
			mainController.print("============================================");
			mainController.print("\tMAIN MENU FOR TASK 3");
			mainController.print("============================================");
			mainController.print("[0] Work with Unit\n" +
					"[1] Work with Pair\n" +
					"[2] Work with Triple\n" +
					"[3] Exit");
			mainController.print("============================================");
			mainController.print("Enter menu item:");
			String input = mainController.read();
			switch (input) {
				case "0":
					unitProcessing(mainController);
					break;
				case "1":
					pairProcessing(mainController);
					break;
				case "2":
					tripleProcessing(mainController);
					break;
				case "3":
					throw new ExitException();
				default:
					mainController.print(String.format("Entered menu item '%s' incorrect, expected 0 - 2", input));
			}
		}

	}

	private void unitProcessing(final MainController mainController) {
		mainController.print("For a class Unit uses the class String");
		mainController.print("Enter value for class String:");
		String input = mainController.read();
		Unit unit = Unit.create(input);
		mainController.print("unit.getValue() return: " + unit.getValue().toString());
		mainController.print("unit.getValue().getClass() return " + unit.getValue().getClass().getName());
	}

	private void pairProcessing(final MainController mainController) {
		mainController.print("For a class Pair uses the classes String and Integer");
		mainController.print("Enter value for class String:");
		String left = mainController.read();
		mainController.print("Enter value for class Integer:");
		String inputRight = mainController.read();
		Integer right;
		try {
			right = Integer.parseInt(inputRight);
		} catch (NumberFormatException e) {
			mainController.print("Value for class Integer incorrect and replace with '0'");
			right = 0;
		}
		Pair pair = Pair.create(left, right);
		mainController.print("pair.getLeft() return: " + pair.getLeft().toString());
		mainController.print("pair.getLeft().getClass() return " + pair.getLeft().getClass().getName());
		mainController.print("pair.getRight() return: " + pair.getRight().toString());
		mainController.print("pair.getRight().getClass() return " + pair.getRight().getClass().getName());
	}

	private void tripleProcessing(final MainController mainController) {
		mainController.print("For a class Pair uses the classes String, Integer and Boolean");
		mainController.print("Enter value for class String:");
		String left = mainController.read();
		mainController.print("Enter value for class Integer:");
		String inputMiddle = mainController.read();
		Integer middle;
		try {
			middle = Integer.parseInt(inputMiddle);
		} catch (NumberFormatException e) {
			mainController.print("Value for class Integer incorrect and replace with '0'");
			middle = 0;
		}
		mainController.print("Enter value for class Boolean:");
		String inputRight = mainController.read();
		Boolean right;
		try {
			right = Boolean.parseBoolean(inputRight);
		} catch (NumberFormatException e) {
			mainController.print("Value for class Integer incorrect and replace with 'false'");
			right = false;
		}
		Triple triple = Triple.create(left, middle, right);
		mainController.print("triple.getLeft() return: " + triple.getLeft().toString());
		mainController.print("triple.getLeft().getClass() return " + triple.getLeft().getClass().getName());
		mainController.print("triple.getMiddle() return: " + triple.getMiddle().toString());
		mainController.print("triple.getMiddle().getClass() return " + triple.getMiddle().getClass().getName());
		mainController.print("triple.getRight() return: " + triple.getRight().toString());
		mainController.print("triple.getRight().getClass() return " + triple.getRight().getClass().getName());
	}
}
