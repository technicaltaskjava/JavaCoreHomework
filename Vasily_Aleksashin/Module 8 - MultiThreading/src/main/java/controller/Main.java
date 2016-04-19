package controller;

import exception.FileIOException;

class Main {

	private Main() {
	}

	public static void main(String[] args) throws FileIOException {
		MainController controller = new MainController();
		controller.run();
	}
}
