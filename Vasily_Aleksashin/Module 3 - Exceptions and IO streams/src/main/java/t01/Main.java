package t01;

import t01.controller.MainController;
import t01.exception.ExitException;

public class Main {
    public static void main(String[] args) {
	    MainController controller = new MainController();
	    try {
		    controller.run();
	    } catch (ExitException e) {

	    }
    }
}
