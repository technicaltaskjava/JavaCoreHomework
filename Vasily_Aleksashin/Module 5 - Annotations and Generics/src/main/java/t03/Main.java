package t03;

import t01.exception.ExitException;
import t03.controller.MainController;

public class Main {

	public static void main(String[] args) {
		MainController controller = new MainController();
		try {
			controller.run();
		} catch (ExitException e) {
			//do nothing
		}
	}


}
