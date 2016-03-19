package javase03.t05.objects;

import java.io.Serializable;

/**
 * Class for films 
 * @author Yury.Vislobodsky
 *
 */
public class Films implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Actors[] actors;
	
	public Films(String name) {
		setName(name);
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getActorsCount() {
		return (actors != null) ? actors.length : 0;
	}
	
	public Actors getActorByIndex(int index) {
		return ((index >= 0) && (index < getActorsCount())) ? actors[index] : null;
	}
	
	public boolean isActorExists(Actors actorSearch) {
		if (actors != null) {
			for (Actors actor : actors) {
				if (actor.equals(actorSearch)) {
					return true;
				}
			}
		}	
		return false;
	}
	
	public void addActor(Actors actor) {
		if (isActorExists(actor)) {
			return;
		}
		Actors[] newActors = new Actors[getActorsCount()+1];
		for (int index = 0; index < getActorsCount(); index++) {
			newActors[index] = actors[index];
		}
		newActors[getActorsCount()] = actor;
		actors = newActors;
	}

	public void deleteActor(Actors actor) {
		if ((getActorsCount() == 0) || !isActorExists(actor)) {
			return;
		}
		Actors[] newActors = new Actors[getActorsCount()-1];
		for (int index = 0; index < getActorsCount()-1; index++) {
			newActors[index] = actors[index];
		}
		actors = newActors;
	}
}
