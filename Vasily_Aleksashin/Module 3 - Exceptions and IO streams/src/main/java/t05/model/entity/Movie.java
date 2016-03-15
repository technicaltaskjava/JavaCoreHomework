package t05.model.entity;

import t01.exception.ModelException;

import java.io.Serializable;
import java.util.Arrays;

public class Movie implements Serializable {
	public static final long serialVersionUID = 1L;

	private final String title;
	private Actor[] actors;

	public Movie(final String title) throws ModelException {
		validate(title);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Actor[] getActors() {
		return actors != null ? Arrays.copyOf(actors, actors.length) : null;
	}

	public boolean find(final String name) throws ModelException {
		validate(name);
		if (actors != null) {
			for (Actor actor : actors) {
				if (actor != null && name.equals(actor.getName())) {
					return true;
				}
			}
		}
		return false;
	}

	public void add(final String name) throws ModelException {
		validate(name);
		add(new Actor(name));
	}

	public void add(final Actor actor) throws ModelException {
		validate(actor);
		if (findMatch(actor) == null) {
			resizeActors();
			actors[actors.length - 1] = actor;
		}
	}

	public void delete(final String name) throws ModelException {
		validate(name);
		delete(new Actor(name));
	}

	public void delete(final Actor actor) throws ModelException {
		validate(actor);
		if (actors != null) {
			for (int index = 0; index < actors.length; index++) {
				if (actors[index] != null && actors[index].equals(actor)) {
					actors[index] = null;
				}
			}
		}
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Movie movie = (Movie) o;

		return title.equals(movie.title);

	}

	@Override
	public int hashCode() {
		return title.hashCode();
	}

	@Override
	public String toString() {
		if (actors == null) {
			return "Movie{ title: '" + title + "\', actors: not found }";
		}
		int count = 0;
		StringBuilder builder = new StringBuilder();
		builder.append("Movie{ title: '").append(title).append("'").append(", actors: ");
		for (Actor actor : actors) {
			if (actor != null) {
				builder.append("'").append(actor.getName()).append("' ");
			} else {
				count++;
			}
		}
		if (count == actors.length) {
			builder.append("not found");
		}
		builder.append(" }");
		return builder.toString();
	}

	private Actor findMatch(final Actor actor) {
		if (actors == null) {
			return null;
		}
		for (Actor act : actors) {
			if (act != null) {
				if (actor.equals(act)) {
					return act;
				}
			}
		}
		return null;
	}

	private void validate(final Object param) throws ModelException {
		if (param == null) {
			throw new ModelException("Parameter can not be NULL");
		}
	}

	private void resizeActors() {
		if (actors == null) {
			actors = new Actor[1];
		} else {
			actors = Arrays.copyOf(actors, actors.length + 1);
		}
	}
}
