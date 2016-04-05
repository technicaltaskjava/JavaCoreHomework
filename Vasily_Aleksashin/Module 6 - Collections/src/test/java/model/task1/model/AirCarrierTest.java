package model.task1.model;

import exception.FileIOException;
import exception.ParameterValidateException;
import model.task1.AirCarrier;
import model.task1.comparator.ComparatorByFlyingRange;
import model.task1.comparator.ComparatorByModel;
import model.task1.entity.Aircraft;
import model.task1.entity.Airfreighter;
import model.task1.entity.Airliner;
import org.junit.Before;
import org.junit.Test;
import utility.FileLoader;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AirCarrierTest {
	private static final String BOEING = "Boeing";
	private static final String AN = "AN";
	private static final String TU = "TU";
	private static final int CAPACITY = 500;
	private static final int TONNAGE = 50;

	private AirCarrier carrier;
	private Aircraft boeing;
	private Aircraft an;


	@Before
	public void setUp() throws ParameterValidateException {
		carrier = new AirCarrier();
		boeing = new Airliner(BOEING);
		boeing.setCapacity(CAPACITY);
		boeing.setTonnage(TONNAGE);
		boeing.setFlyingRange(15_000);
		carrier.add(boeing);
		an = new Airliner(AN);
		an.setCapacity(CAPACITY);
		an.setTonnage(TONNAGE);
		an.setFlyingRange(10_000);
		carrier.add(an);
		Aircraft tu = new Airfreighter(TU);
		tu.setCapacity(CAPACITY);
		tu.setTonnage(TONNAGE);
		tu.setFlyingRange(7_000);
		carrier.add(tu);
	}

	@Test
	public void testLoad() throws FileIOException {
		final String aircraftData = FileLoader.loadFromTxt();
		final int count = carrier.load(aircraftData);
		assertTrue(count == 18);
	}

	@Test
	public void testAdd() throws ParameterValidateException {
		assertTrue(carrier.add(boeing));
	}

	@Test(expected = ParameterValidateException.class)
	public void testAddNull() throws ParameterValidateException {
		assertTrue(carrier.add(null));
	}

	@Test
	public void testRemove() throws ParameterValidateException {
		assertTrue(carrier.remove(an));
	}

	@Test(expected = ParameterValidateException.class)
	public void testRemoveNull() throws ParameterValidateException {
		assertTrue(carrier.remove(null));
	}

	@Test
	public void testGetCapacity() {
		assertTrue(carrier.getCapacity() == CAPACITY * 2);
	}

	@Test
	public void testGetTonnage() {
		assertTrue(carrier.getTonnage() == TONNAGE);
	}

	@Test
	public void testSortByModel() {
		carrier.sort(new ComparatorByModel());
		final String expected = "AirCarrier{\n" +
				"Airliner{model='AN'}\n" +
				"Airliner{model='Boeing'}\n" +
				"Airfreighter{model='TU'}\n" +
				"}";
		assertEquals(expected, carrier.toString());
	}

	@Test
	public void testSortByFlyingRange() {
		carrier.sort(new ComparatorByFlyingRange());
		final String expected = "AirCarrier{\n" +
				"Airfreighter{model='TU'}\n" +
				"Airliner{model='AN'}\n" +
				"Airliner{model='Boeing'}\n" +
				"}";
		assertEquals(expected, carrier.toString());
	}

	@Test
	public void testFindByModel() {
		final List<Aircraft> aircrafts = carrier.find(BOEING, 0, 0, 0);
		assertTrue(aircrafts.contains(boeing));
	}

	@Test
	public void testFindByFlyingRange() {
		final List<Aircraft> aircrafts = carrier.find(null, 0, 0, 10_000);
		assertTrue(aircrafts.size() == 1);
	}

	@Test
	public void testFindByTonnage() {
		final List<Aircraft> aircrafts = carrier.find(null, 0, TONNAGE, 0);
		assertTrue(aircrafts.size() == 1);
	}

	@Test
	public void testFindByCapacity() {
		final List<Aircraft> aircrafts = carrier.find(null, CAPACITY, 0, 0);
		assertTrue(aircrafts.size() == 2);
	}

	@Test
	public void testFindByModelAndCapacity() {
		final List<Aircraft> aircrafts = carrier.find(AN, CAPACITY, 0, 0);
		assertTrue(aircrafts.size() == 1);
	}

	@Test
	public void testFindByModelAndTonnage() {
		final List<Aircraft> aircrafts = carrier.find(AN, 0, TONNAGE, 0);
		assertTrue(aircrafts.size() == 0);
	}

	@Test
	public void testToString() {
		final String expected = "AirCarrier{\n" +
				"Airliner{model='Boeing'}\n" +
				"Airliner{model='AN'}\n" +
				"Airfreighter{model='TU'}\n" +
				"}";
		assertEquals(expected, carrier.toString());
	}

	@Test
	public void testToStringEmpty() {
		carrier = new AirCarrier();
		final String expected = "AirCarrier is EMPTY";
		assertEquals(expected, carrier.toString());
	}
	
}