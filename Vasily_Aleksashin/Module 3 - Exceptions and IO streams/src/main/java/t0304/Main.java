package t0304;

import t01.exception.ExitException;
import t0304.controller.MainController;

public class Main {
	public static void main(String[] args) {
		try {
			MainController controller = new MainController();
			controller.run(args);
		} catch (ExitException e){
			System.out.println("Goodbye");
		}
	}
}
