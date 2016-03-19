package com.epam.task5;

import com.epam.task5.exception.SerializationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Olga Kramska on 14-Mar-16.
 */
public class MovieCollectionTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSerializationDeserializationMovies() {
        String fileName = System.getProperty("user.home") + "\\MoviesCollection.dat";

        MoviesCollection moviesCollection = MoviesCollection.load(fileName);
        Movie[] movies = moviesCollection.getMovies();
        assertNotNull(movies);

        Actor alPacino = new Actor("Al", "Pacino");
        Actor marlonBrando = new Actor("Marlon", "Brando");
        Actor seanPenn = new Actor("Sean", "Penn");

        Movie godfather = new Movie("The Godfather", 1972);
        godfather.setMainActors(new Actor[]{alPacino, marlonBrando});
        moviesCollection.add(godfather);

        Movie carlitosWay = new Movie("Carlito's Way", 1993);
        carlitosWay.setMainActors(new Actor[]{alPacino, seanPenn});
        moviesCollection.add(carlitosWay);

        Movie[] updatedMovies = moviesCollection.getMovies();
        assertEquals(movies.length + 2, updatedMovies.length);

        moviesCollection.delete(carlitosWay);
        assertEquals(updatedMovies.length - 1, moviesCollection.getMovies().length);

        moviesCollection.save(fileName);

        MoviesCollection moviesCollection2 = MoviesCollection.load(fileName);
        Movie[] movies2 = moviesCollection2.getMovies();
        assertArrayEquals(moviesCollection.getMovies(), movies2);
    }

    @Test(expected = SerializationException.class)
    public void testSerializationFault() throws SerializationException {
        String fileName = System.getProperty("user.home") + "\\MoviesCollection.dat";
        MoviesCollection moviesCollection = MoviesCollection.load(fileName);
        moviesCollection.save("ImaginaryDrive:\\xx");
        thrown.expect(SerializationException.class);
    }
}
