package Task5;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oleg on 14.03.2016.
 */

public class FilmCollection implements java.io.Serializable {

    protected Set<Film> filmsSet;

    public FilmCollection() {

        filmsSet = new HashSet<Film>();

    }

    public void addFilmToCollection(Film film) {

        filmsSet.add(film);

    }

    public void removeFilmFromCollection(String filmName) {

        for (Film film :
                filmsSet) {
            if (filmName.equals(film.getFilmName())) {
                filmsSet.remove(film);
                break;
            }
        }
    }

    public Set<Film> getFilmsSet() {
        return filmsSet;
    }

    public Film getFilmFromCollection(String filmName) {
        for (Film film : filmsSet) {
            if (filmName.equals(film.getFilmName())) {
                return film;
            }
        }
        return null;
    }

    public void showFilmsInCollection() {
        for (Film film :
                filmsSet) {
            film.showAll();
        }
    }


}
