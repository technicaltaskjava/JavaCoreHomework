package test.java.com.epam;

import main.java.com.epam.t05.Film;
import main.java.com.epam.t05.FilmsCollection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDeleteFilm {

        @Test
        public void deleteTest() throws Exception {
            FilmsCollection myFilms = new FilmsCollection();
            String[] inputActors = {"Keanu Reeves", "Laurence Fishbourne"};
            myFilms.addFilm("Matrix", inputActors);
            String[] inputActorsNext = {"Vin Diesel", "Paul Walker"};
            myFilms.addFilm("Fast And Furious", inputActorsNext);

            myFilms.deleteFilm("Matrix");

            Film[] testFilms = myFilms.getFilms();
            assertEquals("Fast And Furious", testFilms[0].getFilmName());
            assertEquals(1, myFilms.getActualCountOfFilms());
        }
}
