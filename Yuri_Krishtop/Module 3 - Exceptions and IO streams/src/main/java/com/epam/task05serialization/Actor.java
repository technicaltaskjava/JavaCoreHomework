package com.epam.task05serialization;

public class Actor implements java.io.Serializable {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String toString() {
        return " actor name: " +name+"    actor surname: "+surname;
    }

}
