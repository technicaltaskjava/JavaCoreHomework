package tests.task3;

import task1.Assert;
import task1.Test;
import task3.Unit;

/**
 * @author Sergey Solovyov
 * @version 1.0
 * @since 25.03.2016
 */
public class UnitTest {

    @Test
    public void testUnit(){
        Unit<Integer> unit = Unit.createUnit(new Integer(6));
        Unit<Integer> unit2 = Unit.createUnit(new Integer(6));
        Assert.assertNotEquals("Object equals", unit.equals(unit2), true);
    }
}
