package t05.model;

import org.junit.Before;
import org.junit.Test;
import t01.exception.ModelException;
import t05.model.entity.Actor;
import t05.model.entity.Movie;

import static org.junit.Assert.*;

public class MoviesServiceTest {
	private static final String TITLE = "Matrix";
	private static final String NAME = "Keanu Reeves";
	private MoviesService service;

	@Before
	public void setUp() {
		service = new MoviesService();
	}

	@Test
	public void testAddMovie() throws ModelException {
		service.addMovie(TITLE);
		service.addMovie(TITLE + " 2");
		assertTrue(service.getMovies().length == 2);
	}

	@Test
	public void testAddActor() throws ModelException {
		service.addActor(NAME);
		service.addActor(NAME);
		assertTrue(service.getActors().length == 1);
	}

	@Test
	public void testDeleteMovie() throws ModelException {
		service.addMovie(TITLE);
		service.deleteMovie(TITLE);
		assertNull(service.getMovies()[0]);
	}

	@Test
	public void testDeleteActor() throws ModelException {
		service.addActor(NAME);
		service.deleteActor(NAME);
		assertNull(service.getActors()[0]);
	}

	@Test
	public void testFindMovieByTitle() throws ModelException {
		service.addMovie(TITLE);
		assertTrue(service.findMovieByTitle(TITLE) == 0);
	}

	@Test
	public void testHasMovie() throws ModelException {
		service.addMovie(TITLE);
		assertTrue(service.hasMovie(new Movie(TITLE)) == 0);
	}

	@Test
	public void testAddActorToMovieByName() throws ModelException {
		Movie movie = service.addMovie(TITLE);
		service.addActorToMovie(NAME, movie);
		assertTrue(service.getMovies()[0].getActors()[0].getName().equals(NAME));
	}

	@Test
	public void testAddActorToMovieByActor() throws ModelException {
		Movie movie = service.addMovie(TITLE);
		Actor actor = new Actor(NAME);
		service.addActorToMovie(actor, movie);
		assertTrue(service.getMovies()[0].getActors()[0].equals(actor));
	}

	@Test
	public void testFindActorByName() throws ModelException {
		service.addActor(NAME);
		assertTrue(service.findActorByName(NAME) == 0);
	}

	@Test
	public void testHasActor() throws ModelException {
		Actor actor = new Actor(NAME);
		service.addActor(NAME);
		assertTrue(service.hasActor(actor) == 0);
	}

	@Test
	public void testFindMoviesByActor() throws ModelException {
		Movie movie = service.addMovie(TITLE);
		service.addActorToMovie(NAME, movie);
		assertTrue(service.findMoviesByActor(NAME).length == 1);
	}

	@Test
	public void testFindMoviesByActor1() throws ModelException {
		Movie movie = service.addMovie(TITLE);
		service.addActorToMovie(NAME, movie);
		Actor actor = new Actor(NAME);
		assertTrue(service.findMoviesByActor(actor).length == 1);
	}

	@Test
	public void testGetMovies() {
		assertNull(service.getMovies());
	}

	@Test
	public void testGetActors() {
		assertNull(service.getActors());
	}

	@Test
	public void testToString() {
		String expected = "Movies { EMPTY }";
		assertEquals(expected, service.toString());
	}
}