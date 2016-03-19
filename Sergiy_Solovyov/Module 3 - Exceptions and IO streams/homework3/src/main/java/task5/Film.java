package task5;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 12.03.2016
 */
public class Film implements Serializable{

    private String name;
    private ArrayList<Actor> actors = new ArrayList<Actor>();

    @Override
    public String toString() {
        return "Film: " + name + "\n"+
                actors +
                "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }

    public Film(String name) {
        this.name = name;
    }
}
