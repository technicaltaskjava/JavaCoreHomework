package moviecollection;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexey Ushakov
 */
public class MovieCollectionTest {
    private MovieCollection collection;

    @Before
    public void onStart() {
        collection = new MovieCollection();
    }

    @Test
    public void testGetMoviesCount() throws Exception {
        assertEquals(collection.getMoviesCount(), 0);
        collection.addMovie(new Movie("Test", "Test"));
        assertEquals(collection.getMoviesCount(), 1);
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(collection.isEmpty());
    }

    @Test
    public void testGetMovies() throws Exception {
        Movie[] movies = collection.getMovies();
        assertEquals(movies.length, 0);
        collection.addMovie(new Movie("Test", "Test"));
        movies = collection.getMovies();
        assertEquals(movies.length, 1);

    }

    @Test
    public void testIsMovieInCollection() throws Exception {
        Movie movie = new Movie("Test", "Test");
        collection.addMovie(movie);
        assertTrue(collection.isMovieInCollection(movie));
        assertFalse(collection.isMovieInCollection(new Movie("Test2", "Test2")));
    }

    @Test
    public void testIsCorrectMovieIndex() throws Exception {
        assertFalse(collection.isCorrectMovieIndex(10));
        collection.addMovie(new Movie("Test", "Test"));
        assertTrue(collection.isCorrectMovieIndex(0));

    }

    @Test
    public void testAddMovie() throws Exception {
        assertTrue(collection.isEmpty());
        Movie movie = new Movie("Test", "Test");
        collection.addMovie(movie);
        assertFalse(collection.isEmpty());
        assertTrue(collection.isMovieInCollection(movie));
    }

    @Test
    public void testDeleteMovie() throws Exception {
        assertTrue(collection.isEmpty());
        Movie movie = new Movie("Test", "Test");
        collection.addMovie(movie);
        assertTrue(collection.isMovieInCollection(movie));
        collection.deleteMovie(0);
        assertFalse(collection.isMovieInCollection(movie));
        assertTrue(collection.isEmpty());
    }
}