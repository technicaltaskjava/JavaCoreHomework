package com.epam.taskMultithreading;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import static org.junit.Assert.*;



public class NuberSerchTreadTest
    {
        private Logger myLog = Logger.getLogger("Loger");
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
                        myLog.warning("WORNING" + e);
                        Thread.currentThread().interrupt();
                    }




            }
    }