package com.kokhanyuk.collections.nombersstorage;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class NumbersStorageTest {

    @Test
    public void testAddNumber() throws Exception {
        NumbersStorage nu = new NumbersStorage();
        nu.addNumber(2);
        assertEquals(nu.getNumber((int) 0), 2);
    }

    @Test
    public void testAddNumber1() throws Exception {
        NumbersStorage nu = new NumbersStorage();
        nu.addNumber(0, 1);
        nu.addNumber(1, 3);
        nu.addNumber(2, 5);
        assertEquals(nu.getNumber(1),3);
    }

    @Test
    public void testGetNumber() throws Exception {
        NumbersStorage nu = new NumbersStorage();
        nu.addNumber(0, 1);
        nu.addNumber(1, 8);
        nu.addNumber(2, 155);
        assertEquals(nu.getNumber(2),155);
    }

    @Test
    public void testRemNumber() throws Exception {
        NumbersStorage nu = new NumbersStorage();
        nu.addNumber(0, 1);
        nu.addNumber(1, 8);
        nu.addNumber(2, 155);
        nu.remNumber((Number) 8);
        assertEquals(nu.getNumber(1),155);
    }

    @Test
    public void testRemNumber1() throws Exception {
        NumbersStorage nu = new NumbersStorage();
        nu.addNumber(0, 1);
        nu.addNumber(1, 8);
        nu.addNumber(2, 155);
        nu.remNumber((int) 0);
        assertEquals(nu.getNumber(1),155);
    }

    @Test
    public void testGetSize() throws Exception {
        NumbersStorage nu = new NumbersStorage();
        nu.addNumber(0, 1);
        nu.addNumber(1, 8);
        nu.addNumber(2, 155);
        assertEquals(nu.getSize(),3);
    }
}