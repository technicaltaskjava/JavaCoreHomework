package com.epam.task5;

import com.epam.task5.exception.SerializationException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Olga Kramska on 12-Mar-16.
 */
public class MoviesCollection implements Serializable {
    private Movie[] movies;

    private MoviesCollection() {
        movies = new Movie[0];
    }

    public static MoviesCollection load(String fileName) {
        MoviesCollection moviesCollection;
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(fileName))) {
            moviesCollection = (MoviesCollection) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            moviesCollection = new MoviesCollection();
        }
        return moviesCollection;
    }

    public Movie[] getMovies() {
        return movies;
    }

    public void add(Movie movie) {
        movies = Arrays.copyOf(movies, movies.length + 1);
        movies[movies.length - 1] = movie;
    }

    public void save(String fileName) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
        } catch (IOException e) {
            throw new SerializationException("Can not serialize the object "
                    + this.getClass().getName() + " to file: " + fileName);
        }
    }

    public void delete(Movie movie) {
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].equals(movie)) {
                movies[i] = null;
                break;
            }
        }
        removeNullItem();
    }

    private void removeNullItem() {
        Movie[] update = new Movie[movies.length - 1];
        int j = 0;
        for (Movie movie : movies) {
            if (movie != null) {
                update[j] = movie;
                j++;
            }
        }
        movies = update;
    }

    @Override
    public String toString() {
        return "MoviesCollection" + Arrays.toString(movies);
    }
}

