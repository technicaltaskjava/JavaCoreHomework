package homelist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexey Ushakov
 */
public class HomeListTest {
    HomeList<Integer> homeList;

    @Before
    public void onStart() {
        homeList = new HomeList<>();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(homeList.isEmpty());
    }

    @Test
    public void testContains() {
        homeList.add(1);
        assertTrue(homeList.contains(1));
    }

    @Test
    public void testRemove() {
        homeList.add(3);
        assertTrue(homeList.remove(new Integer(3)));
    }

    @Test
    public void testSize() {
        homeList.add(5);
        assertEquals(1, homeList.size());
    }

    @Test
    public void testGet() {
        homeList.add(5);
        assertTrue(5 == homeList.get(0));
    }

    @Test
    public void testClear() {
        homeList.add(3);
        homeList.clear();
        assertTrue(homeList.isEmpty());
        assertEquals(0, homeList.size());
    }

}