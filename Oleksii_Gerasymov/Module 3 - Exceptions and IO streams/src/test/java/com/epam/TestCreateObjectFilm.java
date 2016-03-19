package test.java.com.epam;

import main.java.com.epam.t05.Film;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestCreateObjectFilm {

    @Test
    public void createTest() throws Exception {
        Film testFilm = new Film();
        assertEquals("", testFilm.getFilmName());
    }
}
