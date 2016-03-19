package com.epam.task05serialization;

import java.util.Arrays;

public class Collection implements java.io.Serializable {
    private String name;
    private Film[] films;

    public Collection(String name, Film... films) {
        this.name = name;
        this.films = new Film[films.length];
        for(Film f : films) {
            this.films = films;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFilms(Film... films) {
        this.films = new Film[films.length];
        for(Film f : films) {
            if (f != null) {
                this.films = films;
            }
        }
    }

    public void deleteFilm(int idFilm) {
        int nonNullElements = 0;
        for(int i = 0; i < films.length; i++) {
            if (films[i] != null) {
                nonNullElements++;
            }
            if(nonNullElements == idFilm){
                films[i] = null;
            }
        }
    }

    public void addFilm(Film newFilm) {
        boolean added = false;
        for(int i = 0; i < films.length; i++) {
            if (films[i] == null) {
                films[i] = newFilm;
                added = true;
            }
        }
        if(!added) {
            Film[] tmpFilms = new Film[films.length + 1];
            tmpFilms = Arrays.copyOf(films, films.length + 1);
            tmpFilms[tmpFilms.length-1] = newFilm;
            films = tmpFilms;
        }
    }

    public void renameFilm(int idFilm, String newName) {
        int nonNullElements = 0;
        for(int i = 0; i < films.length; i++) {
            if (films[i] != null) {
                nonNullElements++;
            }
            if(nonNullElements == idFilm){
                films[i].setName(newName);
            }
        }
    }

    public void deleteActor(int idFilm, int idActor) {
        int nonNullElements = 0;
        for(int i = 0; i < films.length; i++) {
            if (films[i] != null) {
                nonNullElements++;
            }
            if(nonNullElements == idFilm){
                films[i].deleteActor(idActor);
            }
        }
    }

    public void addActor(int idFilm, Actor newActor) {
        int nonNullElements = 0;
        for(int i = 0; i < films.length; i++) {
            if (films[i] != null) {
                nonNullElements++;
            }
            if(nonNullElements == idFilm){
                films[i].addActor(newActor);
            }
        }
    }

    public void renameActor(int idFilm, int idActor, String newName) {
        int nonNullElements = 0;
        for(int i = 0; i < films.length; i++) {
            if (films[i] != null) {
                nonNullElements++;
            }
            if(nonNullElements == idFilm){
                films[i].setActorName(idActor, newName);
            }
        }
    }

    public void resurnameActor(int idFilm, int idActor, String newSurname) {
        int nonNullElements = 0;
        for(int i = 0; i < films.length; i++) {
            if (films[i] != null) {
                nonNullElements++;
            }
            if(nonNullElements == idFilm){
                films[i].setActorSurname(idActor, newSurname);
            }
        }
    }


    public Film[] getFilms() {
        return films;
    }

    public String  toString() {
        int i = 1;
        String filmsList = "";
        for (Film f : films) {
            if(f != null) {
                filmsList += (i++ + ". " + f+"\n");
            }
        }
        return "Collection name: " + name+"\n"+"films: " +"\n"+filmsList;
    }

}
