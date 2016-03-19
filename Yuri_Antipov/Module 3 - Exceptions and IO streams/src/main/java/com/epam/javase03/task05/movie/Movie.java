package com.epam.javase03.task05.movie;

import com.epam.javase03.task05.actors.Actor;

import java.io.Serializable;

public class Movie implements Serializable {
    private String movieName;
    private Actor actor;

    public Movie(String movieName, Actor actor) {
        this.movieName = movieName;
        this.actor = actor;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getActor() {
        return actor.getActorName();
    }

    public void printMovieInfo() {
        System.out.println("Movie \"" + getMovieName() + "\", starring " + getActor());
    }
}
