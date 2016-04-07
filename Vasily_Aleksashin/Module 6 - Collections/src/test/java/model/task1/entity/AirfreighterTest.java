package model.task1.entity;

import exception.ParameterValidateException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AirfreighterTest {
	private static final int SIZE = 100;
	private static final int SIZE_TEST = 1000;
	private static final String MODEL = "AN";
	private static final int ZERO = 0;
	private Aircraft plain;

	@Before
	public void setUp() throws ParameterValidateException {
		plain = new Airfreighter(MODEL);
		plain.setCapacity(SIZE);
		plain.setTonnage(SIZE);
		plain.setFlyingRange(SIZE);
	}

	@Test
	public void testSetTonnage() throws ParameterValidateException {
		plain.setTonnage(SIZE_TEST);
		assertTrue(plain.getTonnage() == SIZE_TEST);
	}

	@Test
	public void testToString() {
		final String expected = "Airfreighter{model='AN'}";
		assertEquals(expected, plain.toString());
	}

	@Test
	public void testGetCapacity() throws ParameterValidateException {
		assertTrue(plain.getCapacity() == ZERO);
	}

	@Test
	public void testSetCapacity() throws ParameterValidateException {
		plain.setCapacity(SIZE_TEST);
		assertTrue(plain.getCapacity() == ZERO);
	}

	@Test
	public void testHashCode() {
		assertTrue(plain.hashCode() == MODEL.hashCode());
	}

	@Test
	public void testEqualsReflexive() {
		//noinspection EqualsWithItself
		assertTrue(plain.equals(plain));
	}

	@Test
	public void testEqualsAirlineSameModel() throws ParameterValidateException {
		assertTrue(plain.equals(new Airfreighter(MODEL)));
	}

	@Test
	public void testEqualsObject() {
		assertFalse(plain.equals(new Object()));
	}

	@Test
	public void testEqualsAirlineDiffModel() throws ParameterValidateException {
		assertFalse(plain.equals(new Airfreighter("")));
	}
}