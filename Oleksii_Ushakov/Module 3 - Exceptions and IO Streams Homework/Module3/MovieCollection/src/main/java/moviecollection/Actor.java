package moviecollection;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Alexey Ushakov
 */
public class Actor implements Serializable {
    private static final long serialVersionUID = 30001000L;
    private String name;
    private String surname;


    public Actor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static Actor getRandomActor() {
        Random random = new Random();
        int index = random.nextInt(20);
        return new Actor("Name" + index, "Surname" + index);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Actor actor = (Actor) obj;
        if (actor.name == null || !actor.name.equals(this.name)) {
            return false;
        }

        if (actor.surname == null || !actor.surname.equals(this.surname)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
