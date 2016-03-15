package t05.controller;

import t01.exception.ExitException;
import t01.exception.ModelException;
import t01.view.View;
import t01.view.impl.ConsoleViewImpl;
import t05.controller.menu.MainMenu;
import t05.model.MoviesService;
import t05.model.SerializeService;

public class MainController {
	public static final String SEPARATOR = "=======================================================";

	private MainMenu mainMenu = new MainMenu();
	private SerializeService serializeService = new SerializeService();
	private View view = new ConsoleViewImpl();
	private MoviesService moviesService;

	public void run() throws ExitException {
		view.print(SEPARATOR);
		try {
			moviesService = serializeService.deserialize(null);
			view.print("The system was successfully deserialize collection of movies from file");
		} catch (ModelException e) {
			view.print("The system failed to deserialize a collection of films from file");
			view.print(e.getMessage());
		}
		view.print(SEPARATOR);
		try {
			mainMenu.show(this);
		} catch (ExitException e) {
			view.close();
			throw e;
		}

	}

	public SerializeService getSerializeService() {
		return serializeService;
	}

	public MoviesService getMoviesService() {
		return moviesService;
	}

	public void print(final String output) {
		view.print(output);
	}

	public String read() {
		return view.read();
	}
}
