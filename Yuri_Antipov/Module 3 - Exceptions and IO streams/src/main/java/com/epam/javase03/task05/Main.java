package com.epam.javase03.task05;

import com.epam.javase03.task05.actors.Actor;
import com.epam.javase03.task05.movie.Movie;
import com.epam.javase03.task05.movieCollections.MoviesCollection;
import com.epam.javase03.task05.serializer.CollectionSaver;

public class Main {

    public static void main(String[] args) {
        System.out.println("Existing movies collection:");
        Actor actorAdams = new Actor("Bob Adams");
        Movie movieSaw = new Movie("Sow III", actorAdams);
        Movie movieShark = new Movie("Shark", actorAdams);
        MoviesCollection collection = new MoviesCollection();
        CollectionSaver saver = new CollectionSaver();

        collection.addMovieToCollection(movieSaw);
        collection.addMovieToCollection(movieShark);
        collection.addMovieToCollection(movieSaw);
        collection.viewMovieList();

        collection.changeActor(movieShark, new Actor("The best actor"));

        saver.serializeCollection(collection.getMoviesList());

        Actor actorHanks = new Actor("Tom Hanks");
        Movie movieGreenMile = new Movie("Green Mile", actorHanks);
        Movie movieUnknown = new Movie("Code Style", actorHanks);

        collection.addMovieToCollection(movieGreenMile);
        collection.addMovieToCollection(movieUnknown);
        System.out.println("Collection after adding new movies:");
        collection.viewMovieList();

        saver.serializeCollection(collection.getMoviesList());
        saver.deserializeCollection();
    }
}
