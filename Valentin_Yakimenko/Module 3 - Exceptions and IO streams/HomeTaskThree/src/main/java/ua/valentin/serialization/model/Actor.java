package ua.valentin.serialization.model;

import java.io.Serializable;

/**
 * Created by valentin.yakimenko on 15.03.16.
 */
public class Actor implements Serializable {
    private String name;
    private String surname;

    public Actor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Actor)) return false;
        Actor entry = (Actor) obj;
        return name.equals(entry.getName())
                && surname.equals(entry.getSurname());
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = hash * 17 + name.hashCode();
        hash = hash * 17 + surname.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

  }


