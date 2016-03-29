package com.epam.task3;

/**
 * Created by Yuriy Krishtop on 28.03.2016.
 */
public class Human {

    private int age;
    private String name;

    public Human(int age, String name) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "Name: " + name + " age: " + age;
    }
}
