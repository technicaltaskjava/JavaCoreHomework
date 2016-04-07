package epam.com.task1;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by O.Gondar on 06.04.2016.
 */
public class TestAirCraft {

    @Test
    public void testAircraftCreation() {
        AirCraft a = new AirCraft(Distances.LONG, 3, 2);
        Assert.assertEquals(Distances.LONG.getDistValue(), a.getFlyDistance().getDistValue());
        Assert.assertTrue(3 == a.getHoldingCapacity());
        Assert.assertTrue(2 == a.getBearingCapacity());

    }

}
