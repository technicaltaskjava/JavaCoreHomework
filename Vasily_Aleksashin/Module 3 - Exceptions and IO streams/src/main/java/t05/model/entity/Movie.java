package t05.model.entity;

import t01.exception.ModelException;

import java.io.Serializable;
import java.util.Arrays;

public class Movie implements Serializable{
	private final String title;
	private Actor[] actors;

	public Movie(final String title, final String... actors) throws ModelException {
		validate(title);
		validate(actors);
		this.title = title;
		if (actors.length != 0) {
			addActors(actors);
		}
	}

	public void addActors(final String... actors) throws ModelException {
		validate(actors);
		for (int index = 0; index < actors.length; index++) {
			resizeActors();
			if (!findMatch(actors[index])) {
				Actor actor = new Actor(actors[index]);
				actor.addMovie(this);
				this.actors[this.actors.length - 1] = actor;
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public Actor[] getActors() {
		return actors != null ? Arrays.copyOf(actors, actors.length + 1) : null;
	}

	private boolean findMatch(final String actor) {
		for (Actor ac : this.actors) {
			if (actor.equals(ac.getName())) {
				return true;
			}
		}
		return false;
	}

	private void resizeActors() {
		if (this.actors == null) {
			this.actors = new Actor[1];
		} else {
			this.actors = Arrays.copyOf(this.actors, this.actors.length + 1);
		}
	}

	private void validate(final Object param) throws ModelException {
		if (param == null) {
			throw new ModelException("Parameter can not be NULL");
		}
	}
}
