package com.epam.task5;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Olga Kramska on 12-Mar-16.
 */
public class Movie implements Serializable {
    private String name;
    private Actor[] mainActors;
    private int yearOfRelease;

    public Movie(String name, int yearOfRelease) {
        this.name = name;
        this.yearOfRelease = yearOfRelease;
    }

    public Actor[] getMainActors() {
        return mainActors;
    }

    public void setMainActors(Actor[] mainActors) {
        this.mainActors = mainActors;
    }

    public String getName() {
        return name;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void addMainActor(Actor actor) {
        if (mainActors == null) {
            mainActors = new Actor[1];
            mainActors[0] = actor;
        } else {
            mainActors = Arrays.copyOf(mainActors, mainActors.length + 1);
            mainActors[mainActors.length - 1] = actor;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return yearOfRelease == movie.yearOfRelease
                && name.equals(movie.name)
                && Arrays.equals(mainActors, movie.mainActors);
    }

    @Override
    public String toString() {
        return "Movie{ " + name + ", " + yearOfRelease +
                "; mainActors are " + Arrays.toString(mainActors) + '}';
    }
}
