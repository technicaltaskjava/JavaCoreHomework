package t05.model;

import org.junit.Before;
import org.junit.Test;
import t01.exception.ModelException;
import t05.model.entity.Actor;
import t05.model.entity.Movie;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SerializeServiceTest {
	private static final String NAME = "Bob";
	private static final String TITLE = "Film";
	private SerializeService serializeService;
	private MoviesService moviesService;

	@Before
	public void setUp() throws ModelException {
		Actor actor = new Actor(NAME);
		Movie movie = new Movie(TITLE);

		serializeService = new SerializeService();
		moviesService = new MoviesService();

		moviesService.addActor(NAME);
		moviesService.addMovie(TITLE);
		moviesService.addActorToMovie(actor, movie);
	}

	@Test
	public void testSerializeCustomFile() throws ModelException {
		String filePath = "src/test/resources";
		File fileTarget = new File(filePath);
		if (fileTarget.exists()) {
			fileTarget.delete();
		}
		File file = serializeService.serialize(moviesService, filePath);
		assertTrue(file.exists());
	}

	@Test
	public void testSerializeDefaultFile() throws ModelException {
		String filePath = "src/test/resources";
		File fileTarget = new File(filePath);
		if (fileTarget.exists()) {
			fileTarget.delete();
		}
		File file = serializeService.serialize(moviesService, null);
		assertTrue(file.exists());
	}

	@Test(expected = ModelException.class)
	public void testSerializeNull() throws ModelException {
		serializeService.serialize(null, null);
	}

	@Test
	public void testDeserializeDefaultFile() throws ModelException {
		assertEquals(moviesService, serializeService.deserialize(null));
	}

	@Test
	public void testDeserializeCustomFile() throws ModelException {
		String filePath = "src/test/resources";
		assertEquals(moviesService, serializeService.deserialize(filePath));
	}
}