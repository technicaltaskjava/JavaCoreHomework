package t05;

import t01.exception.ExitException;
import t05.controller.MainController;

public class Main {
	public static void main(String[] args) {
		MainController controller = new MainController();
		try {
			controller.run();
		} catch (ExitException e) {
			System.out.println("Goodbye");
		}
	}
}
