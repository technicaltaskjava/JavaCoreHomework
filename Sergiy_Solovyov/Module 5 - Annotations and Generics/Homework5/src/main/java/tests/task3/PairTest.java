package tests.task3;

import task1.Assert;
import task1.Test;
import task3.Pair;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 25.03.2016
 */
public class PairTest {

    @Test
    public void testCreateTriplet(){

        Pair<Integer, String> triplet = Pair.createPair(new Integer(3), "Test");
        Pair<Integer, String> triplet2 = Pair.createPair(new Integer(3), "Test");
        Assert.assertNotEquals("Object equals", triplet.equals(triplet2), true);
    }
}
