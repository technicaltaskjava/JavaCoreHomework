package model.task4;

import exception.GetInstanceException;
import exception.ParameterValidateException;
import model.task4.entity.Car;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingTest {
	private static final String[] plates = new String[]{"AE0000EA", "AE1111EA", "AE2222EA"};
	private static final int SIZE = 11;
	private static Car car1;
	private static Car car2;
	private static Car car3;
	private Parking parking;

	@BeforeClass
	public static void init() throws GetInstanceException {
		car1 = Car.getCar(plates[0]);
		car2 = Car.getCar(plates[1]);
		car3 = Car.getCar(plates[2]);
	}

	@Before
	public void setUp() throws GetInstanceException {
		parking = Parking.getInstance(SIZE);
	}

	@Test(expected = GetInstanceException.class)
	public void testGetInstance() throws GetInstanceException {
		Parking.getInstance(0);
	}

	@Test
	public void testComing() throws ParameterValidateException {
		assertTrue(parking.coming(car2));
	}

	@Test
	public void testComingOneCarTwice() throws ParameterValidateException {
		parking.coming(car1);
		assertFalse(parking.coming(car1));
	}

	@Test(expected = ParameterValidateException.class)
	public void testComingNull() throws ParameterValidateException {
		assertTrue(parking.coming(null));
	}

	@Test
	public void testLeaving() throws ParameterValidateException {
		parking.coming(car1);
		assertTrue(parking.leaving(car1));
	}

	@Test(expected = ParameterValidateException.class)
	public void testLeavingNull() throws ParameterValidateException {
		assertTrue(parking.leaving(null));
	}

	@Test
	public void testLeavingNonExistentCar() throws ParameterValidateException {
		assertFalse(parking.leaving(car2));
	}

	@Test
	public void testFreePlacesCount() throws ParameterValidateException {
		parking.coming(car1);
		assertFalse(parking.freePlacesCount() == SIZE);
	}

	@Test(expected = ParameterValidateException.class)
	public void testGetPlaceNumberNull() throws ParameterValidateException {
		parking.getPlaceNumber(null);
	}

	@Test
	public void testGetPlaceNumber() throws ParameterValidateException {
		parking.coming(car1);
		final int expected = 0;
		assertEquals(expected, parking.getPlaceNumber(car1));
	}

	@Test
	public void testGetPlaceNumberNonExistentCar() throws ParameterValidateException {
		final int expected = -1;
		assertEquals(expected, parking.getPlaceNumber(car3));
	}

	@Test
	public void testChangePlaceToBusyPlace() throws ParameterValidateException {
		assertFalse(parking.changePlace(car1, 1));
	}

	@Test
	public void testChangePlaceToFreePlace() throws ParameterValidateException {
		assertTrue(parking.changePlace(car1, 2));
	}

	@Test
	public void testChangePlaceNonExistentCar() throws ParameterValidateException {
		assertFalse(parking.changePlace(car3, 2));
	}

	@Test(expected = ParameterValidateException.class)
	public void testChangePlaceToNonExistentPlace() throws ParameterValidateException {
		parking.changePlace(car1, SIZE + 1);
	}

	@Test
	public void testGetParking() throws ParameterValidateException {
		final String actual = parking.getParking();
		final String expected = "[        ] [        ] [AE0000EA] [        ] [        ] [        ] \n" +
				"[        ] [        ] [        ] [        ] [        ] \n";
		assertEquals(expected, actual);
	}

	@Test
	public void testToString() {
		assertEquals(parking.getParking(), parking.toString());
	}
}