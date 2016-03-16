package t05.controller.menu;

import t01.exception.ModelException;
import t05.model.entity.Movie;

public class MoviesMenu {
	private MainMenu mainMenu;

	public void show(final MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		boolean flag = true;
		while (flag) {
			mainMenu.print(mainMenu.separator);
			String menu = "\tMOVIES MENU\n[1] Add movie | [2] Add actor to movie | [3] Delete movie | [4] Show movies\n" +
					"[5] Find Movie by Title | [6] Find Movies by Actor | [7] back to Main Menu";
			mainMenu.print(menu);
			mainMenu.print(mainMenu.separator);
			mainMenu.print("Enter menu number:");
			String input = mainMenu.read();
			try {
				switch (input) {
					case "1":
						addMovie();
						break;
					case "2":
						addActorToMovie();
						break;
					case "3":
						deleteMovie();
						break;
					case "4":
						mainMenu.print(mainMenu.getMoviesService().toString());
						break;
					case "5":
						findMovieByTitle();
						break;
					case "6":
						findMoviesByActor();
						break;
					case "7":
						flag = false;
						break;
					default:
						mainMenu.print("Incorrect menu number, expected from 1 to 7");
				}
			} catch (Exception e) {
				mainMenu.print("\tIncorrect input");
			}
		}
	}

	private void findMoviesByActor() {
		mainMenu.print("Enter actor NAME:");
		String name = mainMenu.read();
		try {
			Movie[] movies = mainMenu.getMoviesService().findMoviesByActor(name);
			for (Movie movie : movies) {
				mainMenu.print(movie.toString());
			}
		} catch (ModelException e) {
			mainMenu.print(e.getMessage());
		}
	}

	private void findMovieByTitle() {
		mainMenu.print("Enter movie TITLE:");
		String title = mainMenu.read();
		try {
			int movieIndex = mainMenu.getMoviesService().findMovieByTitle(title);
			Movie movie = mainMenu.getMoviesService().getMovies()[movieIndex];
			mainMenu.print(movie.toString());
		} catch (ModelException e) {
			mainMenu.print(e.getMessage());
		}
	}

	private void deleteMovie() {
		mainMenu.print("Enter movie TITLE:");
		String title = mainMenu.read();
		try {
			mainMenu.getMoviesService().deleteMovie(title);
			mainMenu.print("\tOperation successful");
		} catch (ModelException e) {
			mainMenu.print(e.getMessage());
		}
	}

	private void addActorToMovie() {
		mainMenu.print("Enter actor NAME:");
		String name = mainMenu.read();
		mainMenu.print("Enter movie TITLE:");
		String title = mainMenu.read();
		try {
			int movieIndex = mainMenu.getMoviesService().findMovieByTitle(title);
			Movie movie = mainMenu.getMoviesService().getMovies()[movieIndex];
			mainMenu.getMoviesService().addActorToMovie(name, movie);
			mainMenu.print("\tOperation successful");
		} catch (ModelException e) {
			mainMenu.print(e.getMessage());
		}
	}

	private void addMovie() {
		mainMenu.print("Enter movie TITLE:");
		String title = mainMenu.read();
		try {
			mainMenu.getMoviesService().addMovie(title);
			mainMenu.print("\tOperation successful");
		} catch (ModelException e) {
			mainMenu.print(e.getMessage());
		}
	}
}
