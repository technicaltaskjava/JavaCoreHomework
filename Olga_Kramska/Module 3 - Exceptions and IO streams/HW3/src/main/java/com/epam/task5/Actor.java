package com.epam.task5;

import java.io.Serializable;

/**
 * Created by Olga Kramska on 12-Mar-16.
 */
public class Actor implements Serializable {
    private String firstName;
    private String lastName;

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;
        return firstName.equals(actor.firstName) && lastName.equals(actor.lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
