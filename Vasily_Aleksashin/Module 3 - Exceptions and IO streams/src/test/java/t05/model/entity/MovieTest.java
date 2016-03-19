package t05.model.entity;

import org.junit.Before;
import org.junit.Test;
import t01.exception.ModelException;

import static org.junit.Assert.*;

public class MovieTest {
	private static final String TITLE = "Matrix";
	private static final String NAME = "Keanu Reeves";
	private Movie movie;

	@Before
	public void setUp() throws ModelException {
		movie = new Movie(TITLE);
	}

	@Test(expected = ModelException.class)
	public void testMovie() throws ModelException {
		new Movie(null);
	}

	@Test
	public void testGetTitle() {
		assertEquals(TITLE, movie.getTitle());
	}

	@Test
	public void testGetActors() {
		assertNull(movie.getActors());
	}

	@Test(expected = ModelException.class)
	public void testFindNull() throws ModelException {
		movie.find(null);

	}

	@Test
	public void testFindFalse() throws ModelException {
		assertFalse(movie.find("Bob"));
	}

	@Test
	public void testFindTrue() throws ModelException {
		movie.add(NAME);
		assertTrue(movie.find(NAME));
	}

	@Test
	public void testAddActorByName() throws ModelException {
		movie.add(NAME);
		assertTrue(movie.getActors().length == 1);
	}

	@Test
	public void testAddActor() throws ModelException {
		Actor actor = new Actor(NAME);
		movie.add(actor);
		assertTrue(movie.getActors().length == 1);
	}

	@Test
	public void testAddActorTwiceSame() throws ModelException {
		Actor actor = new Actor(NAME);
		movie.add(actor);
		movie.add(actor);
		assertTrue(movie.getActors().length == 1);
	}

	@Test
	public void testAddActorTwiceDiff() throws ModelException {
		Actor actor = new Actor(NAME);
		movie.add(actor);
		movie.add("Laurence Fishburne");
		assertTrue(movie.getActors().length == 2);
	}

	@Test
	public void testDeleteActor() throws ModelException {
		Actor actor = new Actor(NAME);
		movie.add(actor);
		movie.delete(actor);
		assertNull(movie.getActors()[0]);
	}

	@Test
	public void testDeleteActorByName() throws ModelException {
		Actor actor = new Actor(NAME);
		movie.add(actor);
		movie.delete(NAME);
		assertNull(movie.getActors()[0]);
	}

	@Test
	public void testEqualsReflexive() {
		assertTrue(movie.equals(movie));
	}

	@Test
	public void testEqualsName() throws ModelException {
		assertTrue(movie.equals(new Movie(TITLE)));
	}

	@Test
	public void testEqualsNull() throws ModelException {
		assertFalse(movie.equals(null));
	}

	@Test
	public void testEqualsObject() throws ModelException {
		assertFalse(movie.equals(new Object()));
	}

	@Test
	public void testEqualsDiffName() throws ModelException {
		assertFalse(movie.equals(new Movie("Bob")));
	}

	@Test
	public void testHashCode() {
		assertTrue(movie.hashCode() == TITLE.hashCode());
	}

	@Test
	public void testToStringEmptyActors() {
		String expected = "Movie{ title: 'Matrix', actors: not found }";
		assertEquals(expected, movie.toString());
	}

	@Test
	public void testToString() throws ModelException {
		movie.add(NAME);
		String expected = "Movie{ title: 'Matrix', actors: 'Keanu Reeves'  }";
		assertEquals(expected, movie.toString());
	}

	@Test
	public void testToStringDeleteActor() throws ModelException {
		movie.add(NAME);
		movie.delete(NAME);
		String expected = "Movie{ title: 'Matrix', actors: not found }";
		assertEquals(expected, movie.toString());
	}
}