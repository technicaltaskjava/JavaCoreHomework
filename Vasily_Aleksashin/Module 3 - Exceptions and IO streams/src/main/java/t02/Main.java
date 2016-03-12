package t02;

import t01.exception.ModelException;
import t02.controller.MainController;

public class Main {
	public static void main(String[] args) {
		String file = "C:\\Users\\aleksashin\\IdeaProjects\\JavaCoreHomework\\Vasily_Aleksashin\\Module 3 - Exceptions and IO streams\\src\\main\\resources\\various.properties";
		MainController controller = new MainController();
		try {
			controller.load(file);
		} catch (ModelException e) {
			System.out.println(e.getMessage());
		}
	}
}
