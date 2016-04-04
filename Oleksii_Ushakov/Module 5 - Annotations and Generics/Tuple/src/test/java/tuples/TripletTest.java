package tuples;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexey Ushakov
 */
public class TripletTest {

    @Test
    public void testGetFirstValue() throws Exception {
        Triplet<Integer, Integer, Integer> testTriplet = Triplet.create(1, 2, 3);
        assertEquals(testTriplet.getFirstValue().getClass(), Integer.class);
        assertTrue(testTriplet.getFirstValue() == 1);
    }

    @Test(expected = AssertionError.class)
    public void testGetFirstValueFail() throws Exception {
        Triplet<Integer, Integer, Integer> testTriplet = Triplet.create(1, 2, 3);
        assertEquals(testTriplet.getFirstValue().getClass(), Integer.TYPE);
    }

    @Test
    public void testGetSecondValue() throws Exception {
        Triplet<Integer, Integer, Integer> testTriplet = Triplet.create(1, 2, 3);
        assertEquals(testTriplet.getSecondValue().getClass(), Integer.class);
        assertTrue(testTriplet.getSecondValue() == 2);
    }

    @Test(expected = AssertionError.class)
    public void testGetSecondValueFail() throws Exception {
        Triplet<Integer, Integer, Integer> testTriplet = Triplet.create(1, 2, 3);
        assertEquals(testTriplet.getSecondValue().getClass(), Integer.TYPE);
    }


    @Test
    public void testGetThirdValue() throws Exception {
        Triplet<Integer, Integer, Integer> testTriplet = Triplet.create(1, 2, 3);
        assertEquals(testTriplet.getThirdValue().getClass(), Integer.class);
        assertTrue(testTriplet.getThirdValue() == 3);
    }

    @Test(expected = AssertionError.class)
    public void testGetThirdValueFail() throws Exception {
        Triplet<Integer, Integer, Integer> testTriplet = Triplet.create(1, 2, 3);
        assertEquals(testTriplet.getThirdValue().getClass(), Integer.TYPE);
    }

    @Test
    public void testCreate() throws Exception {
        Triplet<Integer, Integer, Integer> testTriplet;
        testTriplet = Triplet.create(1, 2, 3);
        assertNotNull("Triplet not initialized", testTriplet);
    }
}