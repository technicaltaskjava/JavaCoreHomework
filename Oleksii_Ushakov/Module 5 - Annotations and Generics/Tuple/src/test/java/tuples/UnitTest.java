package tuples;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexey Ushakov
 */
public class UnitTest {

    @Test
    public void testGetValue() throws Exception {
        Unit<Integer> testUnit = Unit.create(1);
        assertEquals(testUnit.getValue().getClass(), Integer.class);
        assertTrue(testUnit.getValue() == 1);
    }

    @Test(expected = AssertionError.class)
    public void testGetValueFail() throws Exception {
        Unit<Integer> testUnit = Unit.create(1);
        assertEquals(testUnit.getValue().getClass(), Integer.TYPE);
    }

    @Test
    public void testCreate() throws Exception {
        Unit<Integer> testUnit;
        testUnit = Unit.create(1);
        assertNotNull("Unit not initialized", testUnit);
    }
}