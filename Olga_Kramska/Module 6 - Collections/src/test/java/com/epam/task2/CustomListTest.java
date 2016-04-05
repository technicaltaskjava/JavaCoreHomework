package com.epam.task2;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Olga Kramska on 05-Apr-16.
 */
public class CustomListTest {
    private CustomList<Integer> customList = new CustomList<>();
    private Integer[] array = {12, 15, 1, 20, 3, 7, 25};

    @Test
    public void testIsEmpty() {
        assertTrue(customList.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(customList.add(25));
        assertTrue(customList.contains(25));
        assertFalse(customList.contains(24));
        assertEquals(1, customList.size());
    }

    @Test
    public void testAddSet() {
        assertTrue(customList.addAll(Arrays.asList(array)));
        assertTrue(customList.add(2));
        assertEquals(8, customList.size());
        assertEquals(7, customList.indexOf(2));
        assertEquals(new Integer(12), customList.set(0, 100));
        customList.add(1, 4);
        assertEquals(9, customList.size());
        assertEquals(3, customList.indexOf(1));
    }

    @Test
    public void testToArray() {
        customList.addAll(Arrays.asList(array));
        assertArrayEquals(array, customList.toArray());
    }

    @Test
    public void testRemove() {
        customList.addAll(Arrays.asList(array));
        assertTrue(customList.remove(new Integer(3)));
        assertFalse(customList.remove(new Integer(10)));
        assertEquals(new Integer(1), customList.remove(2));
    }

    @Test
    public void testRemoveAll() {
        customList.addAll(Arrays.asList(array));
        Integer[] integersToRemove = {12, 15, 1, 20, 10};
        Integer[] integersKeep = {3, 7, 25};
        assertTrue(customList.removeAll(Arrays.asList(integersToRemove)));
        assertArrayEquals(integersKeep, customList.toArray());
    }

    @Test
    public void testRetainAll() {
        customList.addAll(Arrays.asList(array));
        Integer[] integersKeep = {1, 3, 7, 25};
        Integer[] integersToRetain = {3, 7, 25, 1, 11, 10};
        assertTrue(customList.retainAll(Arrays.asList(integersToRetain)));
        assertArrayEquals(integersKeep, customList.toArray());
    }

    @Test
    public void testClear() {
        customList.addAll(Arrays.asList(array));
        assertEquals(7, customList.size());
        customList.clear();
        assertEquals(0, customList.size());
    }

    @Test
    public void testGet() {
        customList.add(25);
        assertEquals(new Integer(25), customList.get(0));
        assertEquals(1, customList.size());
    }

    @Test
    public void testIndexOf() {
        customList.addAll(Arrays.asList(array));
        customList.add(12);
        assertEquals(0, customList.indexOf(12));
        assertEquals(7, customList.lastIndexOf(12));
    }

    @Test
    public void testSubList() {
        customList.addAll(Arrays.asList(array));
        List<Integer> subList = customList.subList(0, 3);
        Integer[] subArray = {12, 15, 1};
        assertArrayEquals(subArray, subList.toArray());
    }
}
