package moviecollection;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Alexey Ushakov
 */
public class Movie implements Serializable {
    private static final long serialVersionUID = 30002000L;
    private Actor[] actors;
    private int actorCount;
    private String description;
    private String name;

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;
        this.actorCount = 0;
        this.actors = new Actor[10];
    }

    public static Movie getRandomMovie() {
        Random random = new Random();
        int index = random.nextInt(20);
        Movie movie = new Movie("MovieName" + index, "Description" + index);
        int size = 5 + random.nextInt(5);
        for (int i = 0; i < size; i++) {
            movie.addActor(Actor.getRandomActor());
        }
        return movie;
    }

    private void resizeActorsArray() {
        Actor[] newActors = new Actor[actors.length + actors.length / 2];
        System.arraycopy(actors, 0, newActors, 0, actors.length);
        actors = newActors;
    }

    public void addActor(Actor actor) {
        if (!isActorInMovie(actor)) {
            if (actorCount == actors.length) {
                resizeActorsArray();
            }
            actors[actorCount++] = actor;
        }
    }

    public boolean isActorInMovie(Actor actor) {
        for (int i = 0; i < actorCount; i++) {
            if (actors[i].equals(actor)) {
                return true;
            }
        }
        return false;
    }

    public void deleteActor(Actor actor) {
        for (int i = 0; i < actorCount; i++) {
            if (actors[i].equals(actor)) {
                System.arraycopy(actors, i + 1, actors, i, actors.length - (i + 1));
                actors[actorCount--] = null;
                break;
            }
        }

    }

    public Actor[] getActors() {
        return Arrays.copyOfRange(actors, 0, actorCount);
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Movie movie = (Movie) o;

        if (this.actorCount == movie.actorCount) {
            for (int i = 0; i < movie.actorCount; i++) {
                if (!this.actors[i].equals(movie.actors[i])) {
                    return false;
                }
            }

            if (!this.name.equals(movie.name)) {
                return false;
            }

            if (!this.description.equals(movie.description)) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(actors);
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\"" + name + "\"";
    }


}
