package t05.controller.menu;

import t01.exception.ExitException;
import t05.controller.MainController;
import t05.model.MoviesService;
import t05.model.SerializeService;

public class MainMenu {
	String separator;

	private MainController controller;
	private MoviesService moviesService;
	private SerializeService serializeService;
	private MoviesMenu moviesMenu = new MoviesMenu();
	private SerializeMenu serializeMenu = new SerializeMenu();

	public void show(final MainController controller) throws ExitException {
		this.controller = controller;
		separator = MainController.SEPARATOR;
		moviesService = this.controller.getMoviesService();
		serializeService = this.controller.getSerializeService();
		while (true) {
			print(separator);
			String menu = "\tMAIN MENU\n[1] Serialization Menu | [2] Movies Menu | [3] Exit";
			print(menu);
			print(separator);
			print("Enter menu number:");
			String input = read();
			switch (input) {
				case "1":
					serializeMenu.show(this);
					break;
				case "2":
					moviesMenu.show(this);
					break;
				case "3":
					throw new ExitException();
				default:
					print("Incorrect menu number, expected from 1 to 3");
			}
		}
	}

	MoviesService getMoviesService() {
		return moviesService;
	}

	SerializeService getSerializeService() {
		return serializeService;
	}


	void print(final String output) {
		controller.print(output);
	}

	String read() {
		return controller.read();
	}
}
