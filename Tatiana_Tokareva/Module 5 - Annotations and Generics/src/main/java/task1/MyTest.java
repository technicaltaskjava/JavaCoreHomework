package task1;

import static task1.Assert.assertEquals;

public class MyTest {
    @Test
    public void testAssertionEquals() {
        String expected = "test";
        String actual = "test";
        assertEquals(expected, actual);
    }

    @Test(ignore = true)
    public void testAssertionIgnore() {
        String expected = "test";
        String actual = "test1";
        assertEquals(expected, actual);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAssertionNotEquals() {
        int[] array = new int[1];
        array[1] = 1;
    }
}
