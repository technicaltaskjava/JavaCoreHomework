package task5.films.category.horror;

import task5.films.ActorInFilms;
import task5.films.category.Category;

public class HorroFilms extends Category
    {
        public ActorInFilms TheAbandonedFilm     = new ActorInFilms("The Abandoned Film");
        public ActorInFilms BloodsuckingBastards = new ActorInFilms("Blood sucking Bastards");
        public ActorInFilms BoneTomahawk         = new ActorInFilms("Bone Tomahawk");


       public void addActorsToFilm()
        {
            TheAbandonedFilm.addActor(actor.Jorg);
            TheAbandonedFilm.addActor(actor.Denis);
            TheAbandonedFilm.addActor(actor.Bob);

            BloodsuckingBastards.addActor(actor.Jorg);
            BloodsuckingBastards.addActor(actor.Ken);
            BloodsuckingBastards.addActor(actor.Luci);

            BoneTomahawk.addActor(actor.Lilian);
            BoneTomahawk.addActor(actor.Richard);
            BoneTomahawk.addActor(actor.Maria);
        }






    }
