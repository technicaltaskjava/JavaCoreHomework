package com.epam.task05serialization;

import java.util.Arrays;

public class Film implements java.io.Serializable {
    private String name;
    private Actor[] actors;

    public Film(String name, Actor... actors) {
        this.name = name;
        this.actors = new Actor[actors.length];
        for(Actor a : actors) {
            this.actors = actors;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActors(Actor... actors) {
        this.actors = new Actor[actors.length];
        for(Actor a : actors) {
            this.actors = actors;
        }
    }

    public void setActorName(int idActor, String newName) {
        int nonNullElements = 0;
        for(int i = 0; i < actors.length; i++) {
            if (actors[i] != null) {
                nonNullElements++;
            }
            if(nonNullElements == idActor){
                actors[i].setName(newName);
            }
        }
    }

    public void setActorSurname(int idActor, String newSurname) {
        int nonNullElements = 0;
        for(int i = 0; i < actors.length; i++) {
            if (actors[i] != null) {
                nonNullElements++;
            }
            if(nonNullElements == idActor){
                actors[i].setSurname(newSurname);
            }
        }
    }

    public void deleteActor(int idActor) {
        int nonNullElements = 0;
        for(int i = 0; i < actors.length; i++) {
            if (actors[i] != null) {
                nonNullElements++;
            }
            if(nonNullElements == idActor){
                actors[i] = null;
            }
        }
    }

    public void addActor(Actor newActor) {
        boolean added = false;
        for(int i = 0; i < actors.length; i++) {
            if (actors[i] == null) {
                actors[i] = newActor;
                added = true;
            }
        }
        if(!added) {
            Actor[] tmpActors = new Actor[actors.length + 1];
            tmpActors = Arrays.copyOf(actors, actors.length + 1);
            tmpActors[tmpActors.length-1] = newActor;
            actors = tmpActors;
        }
    }


    public Actor[] getActors() {
        return actors;
    }

    public String  toString() {
        int i = 1;
        String actorsList = "";
        for (Actor a : this.actors) {
            if(a != null) {
                actorsList += ("  "+i++ + ". " + a+"\n");
            }
        }
        return "film name: " + name+"\n"+"  actors: " +"\n"+actorsList;
    }

}
