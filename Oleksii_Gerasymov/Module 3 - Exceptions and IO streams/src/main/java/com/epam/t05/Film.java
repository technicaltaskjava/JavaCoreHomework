package main.java.com.epam.t05;

import java.io.Serializable;

public class Film  implements Serializable {

    private String filmName;
    private String [] actors;

    public Film ()
    {
        this.actors = new String[0];
        this.filmName = "";
    }

    public Film (String filmName, String[] actors)
    {
        this.actors = new String[actors.length];
        this.filmName = filmName;
        System.arraycopy(actors, 0, this.actors, 0, actors.length);
    }

    public String getFilmName() {
        return filmName;
    }

    public String[] getActors() {
        return actors;
    }

}
