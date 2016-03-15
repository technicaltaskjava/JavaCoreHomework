package com.epam.javase03.task05.actors;

import java.io.Serializable;

public class Actor implements Serializable {
    private String actorName;

    public Actor(String actorName) {
        this.actorName = actorName;
    }

    public String getActorName() {
        return actorName;
    }
}
