package model.task1.entity;

import exception.ParameterValidateException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AirlinerTest {
	private static final int SIZE = 100;
	private static final int SIZE_TEST = 1000;
	private static final String MODEL = "Boeing";
	private static final int ZERO = 0;
	private Aircraft plain;

	@Before
	public void setUp() throws ParameterValidateException {
		plain = new Airliner(MODEL);
		plain.setCapacity(SIZE);
		plain.setTonnage(SIZE);
		plain.setFlyingRange(SIZE);
	}

	@Test
	public void testSetTonnage() throws ParameterValidateException {
		assertTrue(plain.getTonnage() == ZERO);
	}

	@Test
	public void testToString() {
		final String expected = "Airliner{model='Boeing'}";
		assertEquals(expected, plain.toString());
	}

	@Test
	public void testGetCapacity() throws ParameterValidateException {
		assertTrue(plain.getCapacity() == SIZE);
	}

	@Test
	public void testSetCapacity() throws ParameterValidateException {
		plain.setCapacity(SIZE_TEST);
		assertTrue(plain.getCapacity() == SIZE_TEST);
	}

	@Test
	public void testGetFlyingRange() {
		assertTrue(plain.getFlyingRange() == SIZE);
	}

	@Test
	public void testSetFlyingRange() throws ParameterValidateException {
		plain.setFlyingRange(SIZE_TEST);
		assertTrue(plain.getFlyingRange() == SIZE_TEST);
	}

	@Test
	public void testGetModel() {
		assertEquals(MODEL, plain.getModel());
	}

	@Test
	public void testGetTonnage() {
		assertTrue(plain.getTonnage() == ZERO);
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
		assertTrue(plain.equals(new Airliner(MODEL)));
	}

	@Test
	public void testEqualsObject() {
		assertFalse(plain.equals(new Object()));
	}

	@Test
	public void testEqualsAirlineDiffModel() throws ParameterValidateException {
		assertFalse(plain.equals(new Airliner("")));
	}
}