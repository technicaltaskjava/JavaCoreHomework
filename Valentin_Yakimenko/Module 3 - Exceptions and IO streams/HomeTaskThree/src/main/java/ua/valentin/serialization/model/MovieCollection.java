package ua.valentin.serialization.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by valentin.yakimenko on 15.03.16.
 */
public class MovieCollection {
    private List<Movie> movies;

    public MovieCollection() {
        movies = new ArrayList<Movie>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(int indexOfMovie) {
        if (isIndexValid(indexOfMovie)) movies.remove(indexOfMovie);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (Movie movie: movies) {
            result.append(count++).append(" : ")
                    .append(movie).append("\n");
        }
        return result.toString();
    }

    public boolean isIndexValid(int indexOfMovie) {
        return indexOfMovie >= 0 && indexOfMovie < movies.size();
    }

    public Set<Actor> getActors() {
        Set<Actor> actors = new HashSet<Actor>();
        for (Movie movie : movies) {
            actors.addAll(movie.getActors());
        }
        return actors;
    }
 }
