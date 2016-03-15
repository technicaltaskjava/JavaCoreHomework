package ua.valentin.serialization.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by valentin.yakimenko on 15.03.16.
 */
public class Movie implements Serializable {
    private String nameOfMovie;
    private Set<Actor> actors;

    public Movie(String nameOfMovie, Set<Actor> actors) {
        this.nameOfMovie = nameOfMovie;
        this.actors = actors;
    }

    public String actorsToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(actors.size()).append(" : [  ");
        for (Actor actor : actors) {
            sb.append(actor).append(" : ");
        }
        sb.delete(sb.length() - 2, sb.length()).append(" ] ");
        return sb.toString();
    }

    public String getNameOfMovie() {
        return nameOfMovie;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Movie)) return false;
        Movie entry = (Movie) obj;
        return nameOfMovie.equals(entry.getNameOfMovie())
                && actors.containsAll(entry.getActors());
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = hash * 17 + nameOfMovie.hashCode();
        for (Actor actor : actors) {
            hash = hash * 17 + actor.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return nameOfMovie + " : " + actorsToString();
    }

 }
