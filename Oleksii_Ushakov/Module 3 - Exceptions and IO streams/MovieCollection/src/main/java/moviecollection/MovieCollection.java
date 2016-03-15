package moviecollection;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Alexey Ushakov
 */

public class MovieCollection implements Serializable {
    private static final long serialVersionUID = 30003000L;
    private Movie[] movies;
    private int moviesCount;

    public MovieCollection() {
        this.moviesCount = 0;
        this.movies = new Movie[10];
    }

    private void resizeMoviesArray() {
        Movie[] newActors = new Movie[movies.length + movies.length / 2];
        System.arraycopy(movies, 0, newActors, 0, movies.length);
        movies = newActors;
    }

    public Movie[] getMovies() {
        return Arrays.copyOfRange(movies, 0, moviesCount);
    }

    public Movie[] getMoviesByActor(Actor actor) {
        int findMoviesCount = 0;
        Movie[] moviesWithActor = new Movie[moviesCount];

        for (int i = 0; i < moviesCount; i++) {
            if (movies[i].isActorInMovie(actor)) {
                moviesWithActor[findMoviesCount++] = movies[i];
            }
        }


        return Arrays.copyOfRange(moviesWithActor, 0, findMoviesCount);
    }

    public int getMoviesCount() {
        return moviesCount;
    }

    public boolean isEmpty() {
        return moviesCount == 0;
    }

    public boolean isMovieInCollection(Movie movie) {
        for (int i = 0; i < moviesCount; i++) {
            if (movies[i].equals(movie)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCorrectMovieIndex(int movieIndex) {
        if (movieIndex >= 0 && movieIndex < moviesCount) {
            return true;
        }
        return false;
    }

    public void addMovie(Movie movie) {
        if (!isMovieInCollection(movie)) {
            if (moviesCount == movies.length) {
                resizeMoviesArray();
            }
            movies[moviesCount++] = movie;
        }
    }

    public void deleteMovie(int index) {
        if ((index >= 0) && (index < movies.length)) {
            System.arraycopy(movies, index + 1, movies, index, movies.length - (index + 1));
            movies[--moviesCount] = null;
        }
    }

    public void fillRandom() {
        for (int i = 0; i < 10; i++) {
            addMovie(Movie.getRandomMovie());
        }
    }
}
