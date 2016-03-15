package test.java.com.epam;

import main.java.com.epam.t05.Film;
import main.java.com.epam.t05.FilmsCollection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAddFilm {
    @Test
    public void addTest() throws Exception {
        FilmsCollection myFilms = new FilmsCollection();

        String[] inputActors = {"Keanu Reeves", "Laurence Fishbourne"};
        myFilms.addFilm("Matrix", inputActors);

        Film[] testFilms = myFilms.getFilms();
        String[] testActors = testFilms[0].getActors();

        assertEquals("Matrix", testFilms[0].getFilmName());
        assertEquals("Keanu Reeves", testActors[0]);
        assertEquals("Laurence Fishbourne", testActors[1]);
        assertEquals(1, myFilms.getActualCountOfFilms());
    }
}
