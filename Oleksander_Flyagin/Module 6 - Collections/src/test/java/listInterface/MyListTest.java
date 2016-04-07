package listInterface;

import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyListTest
    {

        MyList myList;

        @org.junit.Test
        public void testSize()
            {
                myList = new MyList();
                myList.add(12);
                myList.add(23);
                int exp = 2;
                int res = myList.size();
                Assert.assertEquals(exp, res);

            }

        @Test
        public void testIsEmpty()
            {
                myList = new MyList();
                Assert.assertEquals(true, myList.isEmpty());

            }

        @Test
        public void testContains()
            {
                myList = new MyList();
                myList.add(23);
                Assert.assertEquals(true, myList.contains(23));

            }

        @Test
        public void testAdd()
            {
                myList = new MyList();
                myList.add(3);
                int exp = 23;
                int testSize = 1;
                int resSize  = myList.size();
                Assert.assertEquals(myList.add(exp), true);
                Assert.assertEquals(testSize, resSize);

            }

        @Test
        public void testRemove()
            {
                myList = new MyList();
                myList.add(14);
                myList.add(23);
                int res = 23;
                Assert.assertEquals(myList.remove(1), res);

            }

        @Test
        public void testClear()
            {
                myList = new MyList();
                myList.add(14);
                myList.add(23);
                myList.add("Bob");
                myList.clear();
                int size = myList.size();
                Assert.assertEquals(myList.isEmpty(), true);
                Assert.assertEquals(myList.size(), size);


            }

        @Test
        public void testGet()
            {
                myList = new MyList();
                Object a = new Object();
                myList.add(a);
                Assert.assertEquals(a, myList.get(0));

            }

        @Test
        public void testSet()
            {
                myList = new MyList();
                Object a = new Object();
                Object b = new Object();
                myList.add(a);
                myList.set(0,b);
                Assert.assertEquals(b, myList.get(0));

            }


        @Test
        public void testIndexOf()
            {
                myList = new MyList();
                Object a = new Object();
                Object b = new Object();
                myList.add(a);
                myList.add(b);
                int res = 1;
                Assert.assertEquals(res, myList.indexOf(b));
            }
    }