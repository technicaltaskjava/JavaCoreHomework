package epam.com.task3;

import epam.com.task3.NumbersHolder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by O.Gondar on 06.04.2016.
 */
public class TestNumbersHolder {

    private NumbersHolder<Integer> testHolder = new NumbersHolder<Integer>();

    @Before
    public void init() {
        testHolder.setNumbers(0);
        testHolder.setNumbers(1);
        testHolder.setNumbers(2);
    }

    @Test
    public void testAdding() {
        Assert.assertTrue(0 == testHolder.getNumbers(0));
        Assert.assertTrue(2 == testHolder.getNumbers(2));
    }

    @Test
    public void testFindingClosest() {
        testHolder.setNumbers(199);
        testHolder.setNumbers(202);
        Assert.assertEquals(testHolder.findClosest(200), new Integer(199));
    }

}
