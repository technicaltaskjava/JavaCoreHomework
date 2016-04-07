package epam.com.task2;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by O.Gondar on 06.04.2016.
 */
public class TestMyArrayImpl {

    MyArrayListImpl<Integer> test = new MyArrayListImpl<Integer>();

    @Test
    public void testAdd() {
        test.add(111);
        Assert.assertEquals((Integer) 111, test.get(0));
    }

    @Test
    public void testRemove() {

        test.add(000);
        test.add(111);
        test.add(222);
        test.add(333);
        test.remove(2);
        Assert.assertTrue(333 == test.get(2));

    }


}
