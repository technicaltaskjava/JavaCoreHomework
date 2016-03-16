package task5.films.category;

import task5.actors.Actor;
import task5.actors.filmActors.Actors;
import task5.films.ActorInFilms;

public abstract class Category
    {
       public abstract void addActorsToFilm();

        public Actors actor = new Actors();

        public void addActorsToFilm(ActorInFilms film, Actor actor)
            {
                film.addActor(actor);

            }




    }
