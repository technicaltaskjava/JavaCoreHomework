package model.entity;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RecordTest {
	private static final String TITLE = "Test song";
	private static final long DURATION = 5 * 60 * 1000;
	private static final String AUTHOR = "Test author";
	private final String length = String.format("%02d:%02d:%02d", DURATION /1000 / 3600, (DURATION /1000 % 3600) / 60, (DURATION /1000 % 60));
	private Record record;

	@Before
	public void setup() throws ParseException {
		record = new Record(TITLE, AUTHOR, DURATION);
	}

	@Test
	public void testGetTitle() throws Exception {
		assertEquals(TITLE, record.getTitle());
	}

	@Test
	public void testGetAuthor() throws Exception {
		assertEquals(AUTHOR, record.getAuthor());
	}

	@Test
	public void testGetDuration() throws Exception {
		assertEquals(DURATION, record.getDuration());
	}

	@Test
	public void testSetTitle() {
		record.setTitle("New");
		assertFalse(TITLE.equals(record.getTitle()));
	}

	@Test
	public void testSetAuthor() {
		record.setAuthor("New");
		assertFalse(AUTHOR.equals(record.getAuthor()));
	}

	@Test
	public void testSetDuration() {
		record.setDuration(0);
		assertFalse(DURATION == record.getDuration());
	}

	@Test
	public void testEquals() throws Exception {
		assertTrue(record.equals(new Record(TITLE, AUTHOR, DURATION)));
	}

	@Test
	public void testEqualsReflexive() throws Exception {
		assertTrue(record.equals(record));
	}

	@Test
	public void testEqualsNull() throws Exception {
		assertFalse(record.equals(null));
	}

	@Test
	public void testEqualsClass() throws Exception {
		assertFalse(record.equals(new Object()));
	}

	@Test
	public void testEqualsTitle() throws Exception {
		assertFalse(record.equals(new Record("", AUTHOR, DURATION)));
	}

	@Test
	public void testEqualsAuthor() throws Exception {
		assertFalse(record.equals(new Record(TITLE, "", DURATION)));
	}

	@Test
	public void testEqualsDuration() throws Exception {
		assertFalse(record.equals(new Record(TITLE, AUTHOR, 0)));
	}

	@Test
	public void testHashCode() throws Exception {
		int expected = TITLE.hashCode();
		expected = expected * 31 + AUTHOR.hashCode();
		expected = expected * 31 + (int) (DURATION);
		int actual = record.hashCode();
		assertTrue(expected == actual);
	}

	@Test
	public void testToString() throws Exception {
		String expected = "title='" + TITLE + '\'' + ", author='" + AUTHOR + '\'' +", duration=" + length;
		assertEquals(expected, record.toString());
	}
}