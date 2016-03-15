package com.epam.javase03.task05.movieCollections;

import com.epam.javase03.task05.actors.Actor;
import com.epam.javase03.task05.movie.Movie;

import java.io.Serializable;

public class MoviesCollection implements Serializable{
    Movie[] moviesList = new Movie[0];

    public void addMovieToCollection(Movie movie) {
        Movie[] tmp = moviesList;
        moviesList = new Movie[tmp.length + 1];
        moviesList[tmp.length] = movie;
        System.arraycopy(tmp,0,moviesList,0,tmp.length);
    }

    public void viewMovieList() {
        for (Movie movie : moviesList) {
            movie.printMovieInfo();
        }
        System.out.println();
    }

    public Movie[] getMoviesList() {
        return moviesList;
    }
    public void changeActor(Movie movie, Actor actor) {
        movie.setActor(actor);
        System.out.println("Collection after change actor to " + actor.getActorName() +
                " in movie \"" + movie.getMovieName() + "\"");
        for (Movie mov : moviesList) {
            mov.printMovieInfo();
        }
        System.out.println();
    }
}
