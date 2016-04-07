package com.epam;

import com.epam.task.two.MyList;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ListTest {

    @Test
    public void listTest() throws Exception {
        MyList testList = new MyList();
        testList.addElementToList("First");
        testList.addElementToList("Second");
        testList.addElementToList("Third");
        assertEquals(3, testList.getListLength());
        assertEquals("First, Second, Third, ", testList.getList());

        testList.insertElementToList("Six",2);
        assertEquals(4, testList.getListLength());
        assertEquals("First, Six, Second, Third, ", testList.getList());

        testList.changeElementByIndex("Seven",3);
        assertEquals(4, testList.getListLength());
        assertEquals("First, Six, Seven, Third, ", testList.getList());

        testList.removeElementByIndex(3);
        assertEquals(3, testList.getListLength());
        assertEquals("First, Six, Third, ", testList.getList());

        assertEquals("First", testList.getElementByIndex(1));
        assertEquals(3, testList.getIndexByElement("Third"));

    }
}
