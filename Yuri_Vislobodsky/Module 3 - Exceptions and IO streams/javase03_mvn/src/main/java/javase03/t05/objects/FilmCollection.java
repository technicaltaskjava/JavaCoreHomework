package javase03.t05.objects;

import java.io.Serializable;

/**
 * Class for film collection 
 * @author Yury.Vislobodsky
 *
 */
public class FilmCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	private Films[] films;
	
	public int getFilmsCount() {
		return (films != null) ? films.length : 0;
	}

	public Films getFilmByIndex(int index) {
		return ((index >= 0) && (index < getFilmsCount())) ? films[index] : null;
	}	
	
	public boolean isFilmExists(Films filmSearch) {
		if (films != null) {
			for (Films film : films) {
				if (film.equals(filmSearch)) {
					return true;
				}
			}
		}	
		return false;
	}
		
	public void addFilm(Films film) {
		if (isFilmExists(film)) {
			return;
		}
		Films[] newFilms = new Films[getFilmsCount()+1];
		for (int index = 0; index < getFilmsCount(); index++) {
			newFilms[index] = films[index];
		}
		newFilms[getFilmsCount()] = film;
		films = newFilms;
	}

	public void deleteFilm(Films film) {
		if ((getFilmsCount() == 0) || !isFilmExists(film)) {
			return;
		}
		Films[] newFilms = new Films[getFilmsCount()-1];
		for (int index = 0; index < getFilmsCount()-1; index++) {
			newFilms[index] = films[index];
		}
		films = newFilms;
	}
	
	public Actors[] getAllActors() {
		Actors[] actors = null;
		for (Films film : films) {
			for (int index = 0; index < film.getActorsCount(); index++) {
				Actors actor = film.getActorByIndex(index);
				boolean isAlreadyExists = false;
				if (actors != null) {
					for (Actors a : actors) {
						if (a.equals(actor)) {
							isAlreadyExists = true;
							break;
						}
					}
				}
				if (!isAlreadyExists) {
					int actorsCount = (actors != null) ? actors.length : 0;
					Actors[] newActors = new Actors[actorsCount+1];
					for (int indexActor = 0; indexActor < actorsCount; indexActor++) {
						newActors[indexActor] = actors[indexActor];
					}
					newActors[actorsCount] = actor;
					actors = newActors;
				}
			}
		}
		return actors;
	}
	
	public void addActor(int indexFilm, Actors actor) {
		if ((indexFilm >= 0) && (indexFilm < getFilmsCount()) && (actor != null)) {
			films[indexFilm].addActor(actor);
		}
	}
	
	public void deleteActor(Actors actor) {
		if (actor != null) {
			for (Films film : films) {
				film.deleteActor(actor); 
			}
		}
	}	
}
