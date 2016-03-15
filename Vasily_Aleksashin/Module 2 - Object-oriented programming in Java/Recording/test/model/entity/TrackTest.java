package model.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrackTest {
	private static final int ID = 1;

	private Track track;
	@Before
	public void setUp() throws Exception {
		track = new Track(ID);
	}

	@Test
	public void testGetId() throws Exception {
		assertEquals(ID, track.getId());
	}

	@Test
	public void testSetId() throws Exception {
		track.setId(2);
		assertFalse(ID == track.getId());
	}

	@Test
	public void testEquals() throws Exception {
		assertTrue(track.equals(new Track(ID)));
	}

	@Test
	public void testEqualsReflexive() throws Exception {
		assertTrue(track.equals(track));
	}

	@Test
	public void testEqualsNull() throws Exception {
		assertFalse(track.equals(null));
	}

	@Test
	public void testEqualsClass() throws Exception {
		assertFalse(track.equals(new Object()));
	}

	@Test
	public void testEqualsId() throws Exception {
		assertFalse(track.equals(new Track(0)));
	}

	@Test
	public void testHashCode() throws Exception {
		int expected = new Record().hashCode();
		expected = expected * 31 + ID;
		assertEquals(expected, track.hashCode());
	}

	@Test
	public void testToString() throws Exception {
		String expected = "Track{id=" + ID + ", title='', author='', duration=00:00:00}";
		assertEquals(expected, track.toString());
	}
}