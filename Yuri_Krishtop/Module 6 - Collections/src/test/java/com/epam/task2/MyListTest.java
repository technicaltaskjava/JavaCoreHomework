package com.epam.task2;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yuriy Krishtop on 05.04.2016.
 */
public class MyListTest {

    @Test
    public void numbersTest() {
        MyList myList = new MyList();
        assertTrue(myList.add("aa"));
        assertTrue(myList.add("bb"));
        assertTrue(myList.add(11));
        assertTrue(myList.add(22));
        assertTrue(myList.add("cc"));
        assertEquals(5, myList.size());
        assertEquals(11, myList.get(2));
        myList.set(2, "dd");
        assertEquals("dd", myList.get(2));
        assertFalse(myList.isEmpty());
        assertTrue(myList.contains(22));
        assertFalse(myList.contains(33));
        assertTrue(myList.remove("dd"));
        List listArr = new ArrayList();
        listArr.add("eee");
        listArr.add("fff");
        listArr.add("ggg");
        assertTrue(myList.addAll(listArr));
        assertEquals(7, myList.size());
        assertTrue(myList.addAll(1, listArr));
        assertEquals(10, myList.size());
        assertTrue(myList.remove("eee"));
        assertEquals(9, myList.size());
        assertEquals(2, myList.indexOf("ggg"));
        assertEquals(8, myList.lastIndexOf("ggg"));
        assertEquals("fff", myList.subList(1, 3).get(0));
        assertTrue(myList.containsAll(listArr));
        assertTrue(myList.removeAll(listArr));
        assertTrue(myList.addAll(listArr));
        assertTrue(myList.retainAll(listArr));
        myList.clear();
        assertTrue(myList.isEmpty());
    }
}
