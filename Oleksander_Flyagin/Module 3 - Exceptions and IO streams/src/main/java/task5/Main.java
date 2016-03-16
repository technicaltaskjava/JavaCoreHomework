package task5;

import task5.actors.Actor;
import task5.actors.filmActors.Actors;
import task5.collection.CollectionFilms;
import task5.films.ActorInFilms;
import task5.films.category.Category;
import task5.films.category.horror.HorroFilms;
import task5.myExeption.NotFoundFilmsInCollection;
import task5.serialization.SerializMath;

import javax.swing.*;
import java.io.Serializable;

public class Main
    {
        public static void main(String[] args)
            {
                HorroFilms horroFilms = new HorroFilms();
                horroFilms.addActorsToFilm();


                CollectionFilms collectionFilms = new CollectionFilms();
                horroFilms.BoneTomahawk.addActor(new Actor("Jon" , "Dou"));
                horroFilms.BoneTomahawk.dellActors("Richard");

                collectionFilms.addFilm(horroFilms.BoneTomahawk);
                collectionFilms.addFilm(horroFilms.TheAbandonedFilm);
                collectionFilms.addFilm(horroFilms.BloodsuckingBastards);
                collectionFilms.dellFilm("The Abandoned Film");
                collectionFilms.showInfoFilms();
                SerializMath serializMath = new SerializMath();
                serializMath.serialazObject(collectionFilms);
                collectionFilms = new CollectionFilms();
                try
                    {
                        collectionFilms.showInfoFilms();
                    }
                catch (NotFoundFilmsInCollection notFoundFilmsInCollection)
                    {
                        notFoundFilmsInCollection.printStackTrace();
                    }
               collectionFilms = (CollectionFilms) serializMath.deSerialazObject();
                try
                    {
                        collectionFilms.showInfoFilms();
                    }
                catch (NotFoundFilmsInCollection notFoundFilmsInCollection)
                    {
                        notFoundFilmsInCollection.printStackTrace();
                    }


            }






    }
