package t05.model.entity;

import t01.exception.ModelException;

import java.io.Serializable;
import java.util.Arrays;

public class Actor implements Serializable{

	private final String name;
	private Movie[] movies;

	public Actor(final String name) throws ModelException {
		validate(name);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Movie[] getMovies() {
		return movies != null ? Arrays.copyOf(movies, movies.length + 1) : null;
	}

	public void addMovie(final Movie movie) throws ModelException {
		validate(movies);
		resizeMovies();
		if (!findMatch(movie)) {
				this.movies[this.movies.length - 1] = movie;
		}
	}

	private boolean findMatch(final Movie movie) {
		for (Movie m : movies) {
			if (movie.equals(m)) {
				return true;
			}
		}
		return false;
	}

	private void validate(final Object param) throws ModelException {
		if (param == null) {
			throw new ModelException("Parameter can not be NULL");
		}
	}

	private void resizeMovies() {
		if (movies == null) {
			movies = new Movie[1];
		} else {
			movies = Arrays.copyOf(movies, movies.length + 1);
		}
	}
}
