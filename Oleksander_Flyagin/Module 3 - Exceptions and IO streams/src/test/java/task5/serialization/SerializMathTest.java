package task5.serialization;

import org.junit.Assert;
import org.junit.Test;
import task5.collection.CollectionFilms;
import task5.films.category.horror.HorroFilms;

import static org.junit.Assert.*;

public class SerializMathTest
    {

        @Test
        public void testSerialazObject() throws Exception
            {
                CollectionFilms collectionFilms = new CollectionFilms();
                HorroFilms horroFilms = new HorroFilms();
                collectionFilms.addFilm(horroFilms.BoneTomahawk);
                SerializMath serializMath = new SerializMath();
                serializMath.serialazObject(collectionFilms);
                CollectionFilms  newCollectionFilms = (CollectionFilms) serializMath.deSerialazObject();
                Assert.assertEquals(collectionFilms.size(), newCollectionFilms.size());


            }



    }