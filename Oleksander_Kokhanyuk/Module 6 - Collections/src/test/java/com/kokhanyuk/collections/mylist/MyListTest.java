package com.kokhanyuk.collections.mylist;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class MyListTest {
    @Test
    public void testAdd() throws Exception {
        MyList<Integer> list = new MyList();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        int i = list.get(97);
        assertEquals(i, 97);
    }

    @Test
    public void testAdd1() throws Exception {
        MyList<Integer> list = new MyList();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        list.add(95, 1000);
        int i = list.get(95);
        assertEquals(i, 1000);
    }

    @Test
    public void testIsEmpty() throws Exception {
        MyList<Integer> list = new MyList();
        assertEquals(list.isEmpty(), true);
    }

    @Test
    public void testContains() throws Exception {
        MyList<Character> list = new MyList();
        char ch = 'A';
        for (int i = 0; i < 10; i++) {
            list.add(ch++);
        }
        assertEquals(list.contains('F'), true);
    }


    @Test
    public void testRemove() throws Exception {
        MyList<Character> list = new MyList();
        char ch = 'A';
        for (int i = 0; i < 10; i++) {
            list.add(ch++);
        }
        list.remove((Character) 'B');
        assertEquals(list.contains('B'), false);
    }

    @Test
    public void testClear() throws Exception {
        MyList<Character> list = new MyList<>(15);
        char ch = 'A';
        for (int i = 0; i < 15; i++) {
            list.add(ch++);
        }
        list.clear();
        assertEquals(list.isEmpty(),true);
    }

    @Test
    public void testRemove1() throws Exception {
        MyList<Character> list = new MyList();
        char ch = 'A';
        for (int i = 0; i < 10; i++) {
            list.add(ch++);
        }
        list.remove((int) 2);
        assertEquals(list.contains('C'), false);
    }

    @Test
    public void testIndexOf() throws Exception {
        MyList<Character> list = new MyList();
        char ch = 'A';
        for (int i = 0; i < 10; i++) {
            list.add(ch++);
            list.add('C');
        }
        assertEquals(list.indexOf('C'),1);
    }

    @Test
    public void testLastIndexOf() throws Exception {
        MyList<Character> list = new MyList();
        char ch = 'A';
        for (int i = 0; i < 10; i++) {
            list.add(ch++);
            list.add('C');
        }
        assertEquals(list.lastIndexOf('C'),19);
    }
}