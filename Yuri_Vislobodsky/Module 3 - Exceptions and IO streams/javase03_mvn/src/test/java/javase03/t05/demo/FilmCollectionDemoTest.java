package javase03.t05.demo;

import javase03.t05.objects.*;
import javase03.t05.serialization.FilmCollectionSerialization;
import junit.framework.Test;
import junit.framework.TestCase;

public class FilmCollectionDemoTest extends TestCase {
	public void testFilmCollection() throws Exception {
		FilmCollection filmCollection = new FilmCollection();
		filmCollection.addFilm(new Films("Film_1"));
                filmCollection.addFilm(new Films("Film_2"));
                filmCollection.addFilm(new Films("Film_3"));
                filmCollection.addFilm(new Films("Film_4"));
		filmCollection.addActor(0, new Actors("Actor_1"));
                filmCollection.addActor(0, new Actors("Actor_2"));
                filmCollection.addActor(0, new Actors("Actor_3"));
                filmCollection.addActor(1, new Actors("Actor_3"));
                filmCollection.addActor(2, new Actors("Actor_2"));
                filmCollection.addActor(2, new Actors("Actor_3"));
                filmCollection.addActor(3, new Actors("Actor_2"));
                filmCollection.addActor(3, new Actors("Actor_3"));
                filmCollection.addActor(3, new Actors("Actor_4"));

                String fileName = "config_test.txt";
                FilmCollectionSerialization.serializeFilmCollection(fileName, filmCollection);
                FilmCollection filmCollectionCopy = FilmCollectionSerialization.deserializeFilmCollection(fileName);

		for (int indexFilm = 0; indexFilm < filmCollection.getFilmsCount(); indexFilm++) {
			Films film = filmCollection.getFilmByIndex(indexFilm);
			Films filmCopy = filmCollectionCopy.getFilmByIndex(indexFilm);
			if (!film.getName().equals(filmCopy.getName())) {
				fail();
			}
			for (int indexActor = 0; indexActor < film.getActorsCount(); indexActor++) {
				if (!film.getActorByIndex(indexActor).getName().equals(
					filmCopy.getActorByIndex(indexActor).getName())) {
					fail();
				}
			}
		}
	}
}