package test.t02.model;

import t01.model.annotation.Test;
import t01.model.assertion.Assert;
import t02.model.TrackComparator;
import t02.model.entity.Track;

public class TrackComparatorTest {
	private final Track track1 = new Track("Song1", 150);
	private final Track track2 = new Track("Song1", 150);

	@Test
	public void testCompareWithTwoNull() {
		TrackComparator comparator = new TrackComparator();
		Assert.assertTrue(comparator.compare(null, null) == 0);
	}

	@Test
	public void testCompareWithFirstNull() {
		TrackComparator comparator = new TrackComparator();
		Assert.assertTrue(comparator.compare(null, track2) < 0);
	}

	@Test
	public void testCompareWithSecondNull() {
		TrackComparator comparator = new TrackComparator();
		Assert.assertTrue(comparator.compare(track1, null) > 0);
	}

	@Test
	public void testCompareWithTwoTrack() {
		TrackComparator comparator = new TrackComparator();
		Assert.assertTrue(comparator.compare(track1, track1) == 0);
	}
}
