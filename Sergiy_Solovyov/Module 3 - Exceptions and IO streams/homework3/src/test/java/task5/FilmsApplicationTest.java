package task5;

import org.junit.Before;
import org.junit.Test;
import task4.ReadJavaCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 14.03.2016
 */
public class FilmsApplicationTest {
    private FilmsApplication fa = new FilmsApplication();

    public static ArrayList<Film> createFilms(){
        ArrayList<Film> films = new ArrayList<>();

        Film film = new Film("Alpha Dog");
        Actor actor = new Actor("Bruce", "Willis");
        Actor actor2 = new Actor("Sharon", "Stone");
        Actor actor3 = new Actor("Anton", "Yelchin");
        ArrayList<Actor> filmsac1 = new ArrayList<>();
        filmsac1.add(actor);
        filmsac1.add(actor2);
        filmsac1.add(actor3);
        film.setActors(filmsac1);
        films.add(film);

        return films;
    }
    @Before
    public void testWriteFile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ArrayList<Film> films = createFilms();
        Method method = FilmsApplication.class.getDeclaredMethod("writeFile", ArrayList.class, String.class);
        method.setAccessible(true);
        method.invoke(fa, films, "test5");

    }
    @Test
    public void testReadFile() {

        ArrayList<Film> films = fa.readFile("test5");
        String name = films.get(0).getActors().get(1).getFirstName();

        assertEquals("Names are different", name, "Sharon");

    }
}
