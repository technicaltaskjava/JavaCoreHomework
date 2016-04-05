package model.task4.entity;

import exception.GetInstanceException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
	public static final String[] plates = new String[]{"AE7777EA", "AE1111EA", "AE2222EA", "AE3333EA", "AE4444EA"};
	private static Car car;

	@BeforeClass
	public static void setUp() throws GetInstanceException {
		car = Car.getCar(plates[3]);
	}

	@Test(expected = GetInstanceException.class)
	public void testGetCar() throws GetInstanceException {
		Car.getCar(plates[3]);
	}

	@Test(expected = GetInstanceException.class)
	public void testGetCarWithNull() throws GetInstanceException {
		Car.getCar(null);
	}

	@Test
	public void testGetLicensePlate() {
		final String expected = plates[3];
		assertEquals(expected, car.getLicensePlate());
	}

	@Test
	public void testEqualsReflexive() {
		//noinspection EqualsWithItself
		assertTrue(car.equals(car));
	}

	@Test
	public void testEqualsNull() {
		//noinspection ObjectEqualsNull
		assertFalse(car.equals(null));
	}

	@Test
	public void testEqualsNotCar() {
		assertFalse(car.equals(new Object()));
	}

	@Test
	public void testEqualsAnotherCar() throws GetInstanceException {
		assertFalse(car.equals(Car.getCar(plates[4])));
	}

	@Test
	public void testHashCode() {
		final int expected = plates[3].hashCode();
		assertEquals(expected, car.hashCode());
	}

	@Test
	public void testToString() {
		final String expected = String.format("[%s]", plates[3]);
		assertEquals(expected, car.toString());
	}
}