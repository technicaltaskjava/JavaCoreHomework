package model.entity;

import model.entity.enums.Rating;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class SongTest {

	private static final String TITLE = "My song";
	private static final String AUTHOR = "Me";
	private static final long DURATION = 5 * 60 * 1000;
	private static final String ALBUM = "My album";
	private static final Rating RATING = Rating.UNRATED;

	private final String  length = String.format("%02d:%02d:%02d", DURATION /1000 / 3600, (DURATION /1000 % 3600) / 60, (DURATION /1000 % 60));
	private Song song;

	@Before
	public void setup() throws ParseException {
		song = new Song.Builder().title(TITLE).author(AUTHOR).duration(DURATION).album(ALBUM).rating(RATING).build();
	}

	@Test
	public void testGetAlbum() throws Exception {
		assertEquals(ALBUM, song.getAlbum());
	}

	@Test
	public void testSetAlbum() throws Exception {
		song.setAlbum("New");
		assertFalse(ALBUM.equals(song.getAlbum()));
	}

	@Test
	public void testGetRating() throws Exception {
		assertEquals(RATING.getRating(), song.getRating());
	}

	@Test
	public void testSetRating() throws Exception {
		song.setRating(Rating.ONE);
		assertFalse(RATING.getRating() == song.getRating());
	}

	@Test
	public void testEquals() throws Exception {
		assertTrue(song.equals(new Song.Builder().title(TITLE).author(AUTHOR).duration(DURATION).album(ALBUM).rating(RATING).build()));
	}

	@Test
	public void testEqualsReflexive() throws Exception {
		assertTrue(song.equals(song));
	}

	@Test
	public void testEqualsNull() throws Exception {
		assertFalse(song.equals(null));
	}

	@Test
	public void testEqualsClass() throws Exception {
		assertFalse(song.equals(new Object()));
	}

	@Test
	public void testEqualsTitle() throws Exception {
		assertFalse(song.equals(new Song.Builder().title("").author(AUTHOR).duration(DURATION).album(ALBUM).rating(RATING).build()));
	}

	@Test
	public void testEqualsDuration() throws Exception {
		assertFalse(song.equals(new Song.Builder().title(TITLE).author(AUTHOR).duration(0).album(ALBUM).rating(RATING).build()));
	}

	@Test
	public void testEqualsAuthor() throws Exception {
		assertFalse(song.equals(new Song.Builder().title(TITLE).author("").duration(DURATION).album(ALBUM).rating(RATING).build()));
	}

	@Test
	public void testEqualsAlbum() throws Exception {
		assertFalse(song.equals(new Song.Builder().title(TITLE).author(AUTHOR).duration(DURATION).album("").rating(RATING).build()));
	}

	@Test
	public void testEqualsRating() throws Exception {
		assertFalse(song.equals(new Song.Builder().title(TITLE).author(AUTHOR).duration(DURATION).album(ALBUM).rating(Rating.ONE).build()));
	}

	@Test
	public void testHashCode() throws Exception {
		int expected = TITLE.hashCode();
		expected = expected * 31 + AUTHOR.hashCode();
		expected = expected * 31 + (int) (DURATION);
		expected = expected * 31 + ALBUM.hashCode();
		expected = expected * 31 + RATING.hashCode();
		int actual = song.hashCode();
		assertTrue(expected == actual);
	}

	@Test
	public void testToString() throws Exception {
		String expected = "title='" + TITLE +
				"', author='" + AUTHOR +
				"', duration=" + length +
				", album='" + ALBUM +
				"', rating=" + RATING.toString();
		assertEquals(expected, song.toString());
	}
}