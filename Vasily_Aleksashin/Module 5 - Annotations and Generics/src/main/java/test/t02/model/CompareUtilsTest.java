package test.t02.model;

import t01.model.annotation.Test;
import t02.model.CompareUtils;
import t02.model.TrackComparator;
import t02.model.entity.Person;
import t02.model.entity.Track;
import t02.model.exception.ParameterIsNullException;

import java.util.Comparator;

import static t01.model.assertion.Assert.assertEquals;

public class CompareUtilsTest {
	private final Person[] personsElementIsNull = new Person[]{new Person("Bob", 21), null, new Person("Olga", 20)};
	private final Track[] tracksElementIsNull = new Track[]{new Track("I'm sexy and i'm know it", 216), null,
			new Track("Potahat Tik", 195)};
	private final Person[] persons = new Person[]{new Person("Bob", 21), new Person("Tony", 19), new Person("Olga", 20)};
	private final Track[] tracks = new Track[]{new Track("I'm sexy and i'm know it", 216),
			new Track("Gangnam Style", 256),
			new Track("Potahat Tik", 195)};
	private final Comparator<Track> comparator = new TrackComparator();

	@Test
	public void testMaxComparable() throws ParameterIsNullException {
		assertEquals(new Person("Bob", 21), CompareUtils.max(persons));
	}

	@Test
	public void testMinComparable() throws ParameterIsNullException {
		assertEquals(new Person("Tony", 19), CompareUtils.min(persons));
	}

	@Test
	public void testMedianComparable() throws ParameterIsNullException {
		assertEquals(new Person("Olga", 20), CompareUtils.median(persons));
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMaxArrayIsNullComparable() throws ParameterIsNullException {
		CompareUtils.max(null);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMinArrayIsNullComparable() throws ParameterIsNullException {
		CompareUtils.min(null);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMedianArrayIsNullComparable() throws ParameterIsNullException {
		CompareUtils.median(null);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMaxElementIsNullComparable() throws ParameterIsNullException {
		CompareUtils.max(personsElementIsNull);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMinElementIsNullComparable() throws ParameterIsNullException {
		CompareUtils.min(personsElementIsNull);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMedianElementIsNullComparable() throws ParameterIsNullException {
		CompareUtils.median(personsElementIsNull);
	}

	@Test
	public void testMaxComparator() throws ParameterIsNullException {
		assertEquals(new Track("Gangnam Style", 256), CompareUtils.max(tracks, comparator));
	}

	@Test
	public void testMinComparator() throws ParameterIsNullException {
		assertEquals(new Track("Potahat Tik", 195), CompareUtils.min(tracks, comparator));
	}

	@Test
	public void testMedianComparator() throws ParameterIsNullException {
		assertEquals(new Track("I'm sexy and i'm know it", 216), CompareUtils.median(tracks, comparator));
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMaxArrayIsNullComparator() throws ParameterIsNullException {
		CompareUtils.max(null, comparator);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMinArrayIsNullComparator() throws ParameterIsNullException {
		CompareUtils.min(null, comparator);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMedianArrayIsNullComparator() throws ParameterIsNullException {
		CompareUtils.median(null, comparator);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMaxElementIsNullComparator() throws ParameterIsNullException {
		CompareUtils.max(tracksElementIsNull, comparator);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMinElementIsNullComparator() throws ParameterIsNullException {
		CompareUtils.min(tracksElementIsNull, comparator);
	}

	@Test(expected = ParameterIsNullException.class)
	public void testMedianElementIsNullComparator() throws ParameterIsNullException {
		CompareUtils.median(tracksElementIsNull, comparator);
	}
}
