package javase03.t05.objects;

import java.io.Serializable;

/**
 * Class for actors 
 * @author Yury.Vislobodsky
 *
 */
public class Actors implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	
	public Actors(String name) {
		setName(name);
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

