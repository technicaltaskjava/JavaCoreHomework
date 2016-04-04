package tuples;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexey Ushakov
 */
public class PairTest {

    @Test
    public void testGetFirstValue() throws Exception {
        Pair<Integer, Integer> testPair = Pair.create(1, 2);
        assertEquals(testPair.getFirstValue().getClass(), Integer.class);
        assertTrue(testPair.getFirstValue() == 1);
    }

    @Test(expected = AssertionError.class)
    public void testGetFirstValueFail() throws Exception {
        Pair<Integer, Integer> testPair = Pair.create(1, 2);
        assertEquals(testPair.getFirstValue().getClass(), Integer.TYPE);
    }

    @Test
    public void testGetSecondValue() throws Exception {
        Pair<String, String> testPair = Pair.create("Hello", "world");
        assertEquals(testPair.getSecondValue().getClass(), String.class);
        assertTrue(testPair.getSecondValue().equals("world"));
    }

    @Test(expected = AssertionError.class)
    public void testGetSecondValueFail() throws Exception {
        Pair<String, String> testPair = Pair.create("Hello", "world");
        assertEquals(testPair.getFirstValue().getClass(), Object.class);
    }

    @Test
    public void testCreate() throws Exception {
        Pair<Integer, Integer> testPair;
        testPair = Pair.create(1, 2);
        assertNotNull("Pair not initialized", testPair);
    }
}