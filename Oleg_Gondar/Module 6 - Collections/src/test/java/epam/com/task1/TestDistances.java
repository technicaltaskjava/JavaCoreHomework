package epam.com.task1;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by O.Gondar on 06.04.2016.
 */
public class TestDistances {

    @Test
    public void testDistancesValues() {
        Assert.assertTrue(1 == Distances.SHORT.getDistValue());
        Assert.assertTrue(2 == Distances.MIDDLE.getDistValue());
        Assert.assertTrue(3 == Distances.LONG.getDistValue());

    }

}
