package com.task2;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyArrayListTest {

    private MyArrayList<Integer>myArrayList = new MyArrayList<>();

    @Test
    public void testIsEmptyTrue(){
        assertEquals(myArrayList.isEmpty(), true);
    }
    @Test
    public void testIsEmptyFalse(){
        myArrayList.add(3);
        assertEquals(myArrayList.isEmpty(), false);
    }
    @Test
    public void testSize(){
        myArrayList.add(3);
        assertEquals(myArrayList.size(), 1);
    }

    @Test
    public void testContainsTrue(){
        myArrayList.add(3);
        assertEquals(myArrayList.contains(3), true);
    }

    @Test
    public void testIndexOf(){
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(3);
        assertEquals(myArrayList.indexOf(3), 0);
    }

    @Test
    public void testLastIndexOf(){
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(3);
        assertEquals(myArrayList.lastIndexOf(3), 2);
    }
    @Test
    public void testSubList(){
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        List<Integer> list = myArrayList.subList(3, 5);

        assertEquals(list.toString(), "{4, 5, 6}");
    }
    @Test
    public void testRemoveAll(){
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        assertEquals(myArrayList.containsAll(list), true);
        myArrayList.removeAll(list);
        assertEquals(myArrayList.toString(), "{1, 2, 5, 6}");
    }



}
