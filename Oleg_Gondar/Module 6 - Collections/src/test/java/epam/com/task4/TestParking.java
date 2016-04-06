package epam.com.task4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by O.Gondar on 06.04.2016.
 */
public class TestParking {

    Parking parking = new Parking();

    @Before
    public void init() {

        parking.parkCar(0, "test");

    }

    @Test
    public void testParkCar() {

        Assert.assertEquals("test", parking.getCarName(0));
    }

    @Test
    public void testDuplicationNotAllowed() {

        Assert.assertEquals("Car with given name already parked, name of car can not be same", parking.parkCar(0, "test"));
    }

    @Test
    public void testOutOfBound() {
        Assert.assertEquals("Your place out of bound!", parking.parkCar(53, "test1"));
    }

    @Test
    public void testGetFreePlaces() {

        Assert.assertTrue(49 == parking.getFreePlaces());
    }

    @Test
    public void testGetCarPlace() {

        Assert.assertTrue(0 == parking.getCarPlace("test"));

    }

    @Test
    public void testUnparkCar() {
        parking.unparkCar(0);
        Assert.assertTrue(parking.isPlaceFree(0));
    }


}
