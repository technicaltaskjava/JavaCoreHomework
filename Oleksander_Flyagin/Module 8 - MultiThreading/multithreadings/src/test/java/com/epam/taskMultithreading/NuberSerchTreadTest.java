package com.epam.taskMultithreading;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;



public class NuberSerchTreadTest
    {
        List  exp = new ArrayList();
        private void setCollextion()
            {
                exp.add(2);
                exp.add(3);
                exp.add(5);
                exp.add(7);
                exp.add(11);
                exp.add(13);
            }

        @Test
        public void testGetNuberList()
            {
                CopyOnWriteArrayList test = new CopyOnWriteArrayList();
                setCollextion();
                int start = 1;
                int last = 15;
                NuberSerchTread  myTreadGetList = new NuberSerchTread(start, last, test);
                try
                    {
                        myTreadGetList.join();
                        Assert.assertArrayEquals( test.toArray() , exp.toArray());
                    }
                catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }




            }
    }