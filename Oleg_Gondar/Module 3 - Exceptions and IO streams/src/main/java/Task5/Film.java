package Task5;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oleg on 14.03.2016.
 */
public class Film implements java.io.Serializable {

    private String filmName;

    private Set<Actor> actors;

    public Film() {
        actors = new HashSet<Actor>();
    }

    public Film(String filmName, Set<Actor> actors) {
        this.filmName = filmName;
        this.actors = actors;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public void setActor(Actor actor) {
        actors.add(actor);
    }

    public void showAll() {
        System.out.println(filmName);
        for (Actor a : actors) {
            System.out.println("Actor: " + a.getActorFirstName() + " " + a.getActorLastName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        return getFilmName() != null ? getFilmName().equals(film.getFilmName()) : film.getFilmName() == null;

    }

    @Override
    public int hashCode() {
        return getFilmName() != null ? getFilmName().hashCode() : 0;
    }
}
