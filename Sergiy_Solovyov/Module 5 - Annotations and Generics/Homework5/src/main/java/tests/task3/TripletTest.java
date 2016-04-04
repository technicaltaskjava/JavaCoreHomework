package tests.task3;

import task1.Assert;
import task1.Test;
import task3.Triplet;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 25.03.2016
 */
public class TripletTest {

    @Test
    public void testCreateTriplet(){

        Triplet<Integer, String, Integer> triplet = Triplet.createTriplet(new Integer(3), "Test", new Integer(8));
        Triplet<Integer, String, Integer> triplet2 = Triplet.createTriplet(new Integer(3), "Test", new Integer(8));
        Assert.assertNotEquals("Object equals", triplet.equals(triplet2), true);
    }
}
