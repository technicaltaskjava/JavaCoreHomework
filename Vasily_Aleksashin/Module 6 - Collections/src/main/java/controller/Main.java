package controller;

import exception.ExitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	private Main() {
	}

	public static void main(String[] args) {
		MainController controller = new MainController();
		try {
			controller.run();
		} catch (ExitException e) {
			logger.info(e.getMessage(), e);
		}
	}
}
