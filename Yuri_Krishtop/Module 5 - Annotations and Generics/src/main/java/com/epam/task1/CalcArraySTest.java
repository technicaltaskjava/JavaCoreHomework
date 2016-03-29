package com.epam.task1;

/**
 * Created by Yuriy Krishtop on 26.03.2016.
 */
public class CalcArraySTest {

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetWrongElement() {
        Calculator myCalc;
        myCalc = new Calculator();
        int[] array = {20, 30, 10};
        assert 30 == myCalc.getArrayElement(array, 3) : ArrayIndexOutOfBoundsException.class;
    }

    @Test(ignore = true)
    public void testGetMaxElement() {
        Calculator myCalc;
        myCalc = new Calculator();
        int[] array = {20, 30, 10};
        assert 30 == myCalc.getArrayElement(array, 2);
    }

    @Test()
    public void testMinElement() {
        Calculator myCalc;
        myCalc = new Calculator();
        int[] array = {20, 30, 10};
        assert 10 == myCalc.getArrayElement(array, 0);
    }

    @Test(expected = NullPointerException.class)
    public void testGetInteger() {
        Calculator myCalc;
        myCalc = new Calculator();
        int[] array = {20, 30, 10};
        assert 0 == myCalc.getInteger(0).hashCode() : NullPointerException.class;
    }

}
