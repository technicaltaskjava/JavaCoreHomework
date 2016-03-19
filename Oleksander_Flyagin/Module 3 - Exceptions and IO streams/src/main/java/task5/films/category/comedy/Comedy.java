package task5.films.category.comedy;

import task5.actors.Actor;
import task5.films.ActorInFilms;
import task5.films.category.Category;

public class Comedy extends Category
    {
        public ActorInFilms  MeanGirls     = new ActorInFilms("Mean Girls");
        public ActorInFilms  ManUp         = new ActorInFilms("Man Up");
        public ActorInFilms  FreakyFriday  = new ActorInFilms("Freaky Friday");


        public void addActorsToFilm()
            {
                MeanGirls.addActor(actor.Rob);
                MeanGirls.addActor(actor.Ken);
                MeanGirls.addActor(actor.Ketrin);

                ManUp.addActor(actor.Deric);
                ManUp.addActor(actor.Lilian);
                ManUp.addActor(actor.Denis);

                FreakyFriday.addActor(actor.Lilian);
                FreakyFriday.addActor(actor.Deric);
                FreakyFriday.addActor(actor.Maria);

            }


    }
