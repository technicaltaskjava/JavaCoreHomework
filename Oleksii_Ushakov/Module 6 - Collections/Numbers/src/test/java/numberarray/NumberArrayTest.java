package numberarray;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexey Ushakov
 */
public class NumberArrayTest {
    private NumberArray<Integer> array;

    @Before
    public void onStart() {
        array = new NumberArray<>();
    }

    @Test
    public void testSize() {
        assertTrue(array.isEmpty());
        array.add(1);
        assertEquals(1, array.size());
    }

    @Test
    public void testAdd() {
        assertTrue(array.isEmpty());
        array.add(1);
        assertFalse(array.isEmpty());
    }

    @Test
    public void testRemove() {
        assertTrue(array.isEmpty());
        array.add(1);
        assertFalse(array.isEmpty());
        array.remove(1);
        assertTrue(array.isEmpty());
    }

    @Test
    public void testLogic() {
        array = new NumberArray<>(new Integer[]{ 0, 7, 9, 5 });
        assertEquals(Integer.valueOf(0), array.find(2));
        assertEquals(Integer.valueOf(5), array.find(4));
        assertEquals(Integer.valueOf(9), array.find(100));
    }
}