package parking;

import car.Car;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexey Ushakov
 */
public class ParkingTest {
    private Parking parking;

    @Before
    public void onStart() {
        parking = new Parking(10);
    }

    @Test
    public void testGetFreePlacesCount() {
        assertEquals(10, parking.getFreePlacesCount());
    }

    @Test
    public void testGetNearSpace() {
        assertEquals(0, parking.getNearSpace());
        parking.carArrived(new Car("АЕ 1897 КА"));
        assertEquals(1, parking.getNearSpace());
    }

    @Test
    public void testGetNearSpaceByIndex() {
        assertEquals(6, parking.getNearSpace(6));
    }

    @Test
    public void testIsFreeSpace() {
        assertTrue(parking.isFreeSpace(6));
        parking.carArrived(6, new Car("АП 9825 КВ"));
        assertFalse(parking.isFreeSpace(6));
    }

    @Test
    public void testCarArrived() {
        parking.carArrived(6, new Car("АП 9875 КВ"));
        assertFalse(parking.isFreeSpace(6));
    }

    @Test
    public void testCarArrivedByIndex() {
        parking.carArrived(new Car("АП 6751 КВ"));
        assertFalse(parking.isFreeSpace(0));
    }

    @Test
    public void testCarLeft() {
        Car car = new Car("АП 9875 КВ");
        parking.carArrived(car);
        assertEquals(9, parking.getFreePlacesCount());
        parking.carLeft(car);
        assertEquals(10, parking.getFreePlacesCount());
    }

    @Test(expected = NoSuchElementException.class)
    public void testCarLeftCatchException() {
        parking.carLeft(new Car("АП 2975 КВ"));
    }

    @Test
    public void testCarLeftByIndex() {
        Car car = new Car("АП 6715 КВ");
        parking.carArrived(6, car);
        assertEquals(9, parking.getFreePlacesCount());
        parking.carLeft(6);
        assertEquals(10, parking.getFreePlacesCount());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCarLeftByIndexCatchException() {
        parking.carLeft(-5);
    }
}