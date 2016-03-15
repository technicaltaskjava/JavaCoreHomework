package model.entity.enums;

import model.entity.enums.Rating;
import org.junit.Test;

import static org.junit.Assert.*;

public class RatingTest {
	@Test
	public void testGetRatingUnrated() throws Exception {
		int expected = 0;
		int actual = Rating.UNRATED.getRating();
		assertTrue(expected == actual);
	}

	@Test
	public void testToStringRated() {
		assertEquals("\u2605", Rating.ONE.toString());
	}

	@Test
	public void testToStringUnRated() {
		assertEquals("unrated", Rating.UNRATED.toString());
	}

	@Test
	public void testGetRatingWithNull() {
		assertNull(Rating.getRating(null));
	}

	@Test
	public void testGetRating() {
		assertEquals(Rating.ONE, Rating.getRating(1));
	}

	@Test
	public void testGetRatingWrongRating() {
		assertNull(Rating.getRating(6));
	}
}