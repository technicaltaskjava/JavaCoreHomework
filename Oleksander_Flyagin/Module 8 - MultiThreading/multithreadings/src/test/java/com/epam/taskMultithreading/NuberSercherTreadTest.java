package com.epam.taskMultithreading;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NuberSercherTreadTest {
    List test = new ArrayList();

    private void setCollextion() {
        test.add(2);
        test.add(3);
        test.add(5);
        test.add(7);
        test.add(11);
        test.add(13);
    }

    @Test
    public void testGetNuberList1() {
        int start = 1;
        int last = 15;
        NuberSercherTread myTreadGetNubers = new NuberSercherTread();
        myTreadGetNubers.setBegin(start);
        myTreadGetNubers.setLast(last);
        myTreadGetNubers.run();
        setCollextion();
        Assert.assertArrayEquals(test.toArray(), myTreadGetNubers.getNuberList().toArray());


    }


    @Test
    public void testGetNuberList2() {
        int start = 1;
        int last = 15;
        NuberSercherTread myTreadGetNubers = new NuberSercherTread();
        myTreadGetNubers.setBegin(start);
        myTreadGetNubers.setLast(last);
        myTreadGetNubers.run();
        setCollextion();
        Assert.assertEquals(test.size(), myTreadGetNubers.getNuberList().size());


    }


}