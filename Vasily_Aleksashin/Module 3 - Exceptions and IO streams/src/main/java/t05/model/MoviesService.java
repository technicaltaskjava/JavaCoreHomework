package t05.model;

import t01.exception.ModelException;
import t05.model.entity.Actor;
import t05.model.entity.Movie;

import java.io.Serializable;
import java.util.Arrays;

public class MoviesService implements Serializable {
	public static final long serialVersionUID = 1L;

	private Movie[] movies = null;
	private Actor[] actors = null;

	public Movie addMovie(final String title) throws ModelException {
		validate(title);
		if (findMovieByTitle(title) == -1) {
			movies = resizeMovies(movies);
			return movies[movies.length - 1] = new Movie(title);
		}
		return null;
	}

	public Actor addActor(final String name) throws ModelException {
		validate(name);
		if (findActorByName(name) == -1) {
			actors = resizeActors(actors);
			return actors[actors.length - 1] = new Actor(name);
		}
		return null;
	}

	public void deleteMovie(final String title) throws ModelException {
		validate(title);
		int movieIndex = findMovieByTitle(title);
		if (movieIndex != -1) {
			movies[movieIndex] = null;
		}
	}

	public void deleteActor(final String name) throws ModelException {
		validate(name);
		int actorIndex = findActorByName(name);
		if (actorIndex != -1) {
			actors[actorIndex] = null;
		}
	}

	public int findMovieByTitle(final String title) throws ModelException {
		validate(title);
		if (movies != null) {
			for (int index = 0; index < movies.length; index++) {
				if (movies[index] != null) {
					if (title.equals(movies[index].getTitle())) {
						return index;
					}
				}
			}
		}
		return -1;
	}

	public int hasMovie(final Movie movie) throws ModelException {
		validate(movie);
		return findMovieByTitle(movie.getTitle());
	}

	public void addActorToMovie(final Actor actor, final Movie movie) throws ModelException {
		validate(actor);
		validate(movie);
		addActorToMovie(actor.getName(), movie);
	}

	public void addActorToMovie(final String name, final Movie movie) throws ModelException {
		validate(name);
		validate(movie);
		int movieIndex = hasMovie(movie);
		if (movieIndex != -1) {
			int actorIndex = findActorByName(name);
			if (actorIndex == -1) {
				actors = resizeActors(actors);
				actors[actors.length - 1] = new Actor(name);
				actorIndex = actors.length - 1;
			}
			movies[movieIndex].add(actors[actorIndex]);
		}
	}

	public int findActorByName(final String name) throws ModelException {
		validate(name);
		if (actors != null) {
			for (int index = 0; index < actors.length; index++) {
				if (actors[index] != null) {
					if (name.equals(actors[index].getName())) {
						return index;
					}
				}
			}
		}
		return -1;
	}

	public int hasActor(final Actor actor) throws ModelException {
		validate(actor);
		return findActorByName(actor.getName());
	}

	public Movie[] findMoviesByActor(final Actor actor) throws ModelException {
		validate(actor);
		return findMoviesByActor(actor.getName());

	}

	public Movie[] findMoviesByActor(final String name) throws ModelException {
		validate(name);
		Movie[] result = null;
		if (movies != null) {
			for (int index = 0; index < movies.length; index++) {
				if (movies[index] != null) {
					if (movies[index].find(name)) {
						result = resizeMovies(result);
						result[result.length - 1] = movies[index];
					}
				}
			}
		}
		return result;
	}

	public Movie[] getMovies() {
		return movies != null ? Arrays.copyOf(movies, movies.length) : null;
	}

	public Actor[] getActors() {
		return actors != null ? Arrays.copyOf(actors, actors.length) : null;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MoviesService service = (MoviesService) o;

		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(movies, service.movies)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return Arrays.equals(actors, service.actors);

	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(movies);
		result = 31 * result + Arrays.hashCode(actors);
		return result;
	}

	@Override
	public String toString() {
		if (movies == null) {
			return "Movies { EMPTY }";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("Movies:");
		for (Movie movie : movies) {
			if (movie != null) {
				builder.append("\n").append(movie.toString());
			}
		}
		return builder.toString();
	}

	private void validate(final Object param) throws ModelException {
		if (param == null) {
			throw new ModelException("Parameter can not be NULL");
		}
	}

	private Movie[] resizeMovies(Movie[] array) {
		if (array == null) {
			return new Movie[1];
		} else {
			return Arrays.copyOf(array, array.length + 1);
		}
	}

	private Actor[] resizeActors(Actor[] array) {
		if (array == null) {
			return new Actor[1];
		} else {
			return Arrays.copyOf(array, array.length + 1);
		}
	}
}
