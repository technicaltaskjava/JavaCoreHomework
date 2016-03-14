package model.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiskTest {
	private static final String LABEL = "My disk";
	private static final int DURATION = 420000;
	private static final long MAX_DURATION_MILL = 80 * 60 * 1000;
	private static final int MAX_DISK_TIME_MINUTES = 80;

	private Disk disk;
	private Record song;
	@Before
	public void setUp() throws Exception {
		disk = new Disk(LABEL, MAX_DISK_TIME_MINUTES);
		song = new Song.Builder().title("My song").author("Me").duration(DURATION).build();
	}

	@Test
	public void testGetLabel() throws Exception {
		assertTrue(LABEL.equals(disk.getLabel()));
	}

	@Test
	public void testAddTrack() {
		assertTrue(disk.addTrack(song));
	}

	@Test
	public void testAddTrackFalse() {
		for (int index = 0; index < 12; index++) {
			disk.addTrack(song);
		}
		assertFalse(disk.addTrack(song));
	}

	@Test
	public void testGetTracks() throws Exception {
		disk.addTrack(song);
		disk.addTrack(song);
		assertTrue(disk.getTracks().length == 2);
	}

	@Test
	public void testGetCurrentDuration() throws Exception {
		disk.addTrack(song);
		assertEquals(DURATION, disk.getCurrentDuration());
	}

	@Test
	public void testToString() throws Exception {
		String expected = "Disk {label: My disk\n" +
				"\tTrack{id=1, title='My song', author='Me', duration=00:07:00}}\n" +
				"Total used duration=00:07:00";
		disk.addTrack(song);
		assertEquals(expected, disk.toString());
	}

	@Test
	public void testGetMaxDuration() {
		assertTrue(disk.getMaxDuration() == MAX_DURATION_MILL);
	}
}