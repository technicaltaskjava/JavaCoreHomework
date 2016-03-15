package t05.model.entity;

import t01.exception.ModelException;

import java.io.Serializable;

public class Actor implements Serializable{
	public static final long serialVersionUID = 1L;

	private final String name;

	public Actor(final String name) throws ModelException {
		if (name == null) {
			throw new ModelException("Parameter can not be NULL");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Actor actor = (Actor) o;

		return name.equals(actor.name);

	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return "Actor{" +
				"name='" + name + '\'' +
				'}';
	}
}
