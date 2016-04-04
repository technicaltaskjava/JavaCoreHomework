package compareutils;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexey Ushakov
 */
public class CompareUtilsTest {
    private Integer[] testArray = new Integer[]{ 1, 5, 5, 5, 5, 5, 8, 7, 9, 3, 1, 2 };
    private Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer first, Integer second) {
            if (first > second) {
                return 1;
            }
            if (first < second) {
                return -1;
            }
            return 0;
        }
    };

    @Test
    public void testMinComparable() throws Exception {
        int min = CompareUtils.min(testArray);
        assertEquals(min, 1);
    }

    @Test
    public void testMaxComparable() throws Exception {
        int max = CompareUtils.max(testArray);
        assertEquals(max, 9);
    }

    @Test
    public void testMedianComparable() throws Exception {
        int median = CompareUtils.median(testArray);
        assertEquals(median, 5);
    }

    @Test
    public void testMinComparator() throws Exception {
        int min = CompareUtils.min(testArray,comparator);
        assertEquals(min, 1);
    }

    @Test
    public void testMaxComparator() throws Exception {
        int max = CompareUtils.max(testArray,comparator);
        assertEquals(max, 9);
    }

    @Test
    public void testMedianComparator() throws Exception {
        int median = CompareUtils.median(testArray,comparator);
        assertEquals(median, 5);
    }
}