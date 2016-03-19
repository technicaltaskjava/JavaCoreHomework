package com.epam.task5;

import java.util.Arrays;

/**
 * Created by Olga Kramska on 12-Mar-16.
 */
public class MainTask5 {
    public static void main(String[] args) {
        String fileName = System.getProperty("user.home") + "\\MoviesCollection.dat";

        MoviesCollection moviesCollection = MoviesCollection.load(fileName);
        System.out.println(Arrays.toString(moviesCollection.getMovies()));

        Actor alPacino = new Actor("Al", "Pacino");
        Actor marlonBrando = new Actor("Marlon", "Brando");
        Actor seanPenn = new Actor("Sean", "Penn");

        Movie godfather = new Movie("The Godfather", 1972);
        godfather.setMainActors(new Actor[]{alPacino, marlonBrando});
        moviesCollection.add(godfather);

        Movie carlitosWay = new Movie("Carlito's Way", 1993);
        carlitosWay.addMainActor(alPacino);
        carlitosWay.addMainActor(seanPenn);
        moviesCollection.add(carlitosWay);

        Movie panic = new Movie("The Panic in Needle Park", 1971);
        panic.addMainActor(alPacino);
        moviesCollection.add(panic);

        Movie scarface = new Movie("Scarface", 1983);
        scarface.addMainActor(alPacino);
        moviesCollection.add(scarface);

        System.out.println(Arrays.toString(moviesCollection.getMovies()));

        moviesCollection.delete(panic);
        System.out.println(Arrays.toString(moviesCollection.getMovies()));

        moviesCollection.save(fileName);
    }
}
