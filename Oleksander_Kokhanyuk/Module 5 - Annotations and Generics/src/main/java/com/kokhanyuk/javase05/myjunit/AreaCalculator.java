package com.kokhanyuk.javase05.myjunit;

/**
 * AreaCalculator
 * <p/>
 * This class contains methods for calculating the area of some figures.
 * This class is the subject of testing.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class AreaCalculator {
    public double areaRange(int radius) throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException();
        }
        return 3.14 * radius * radius;
    }

    public int areaRectangle(int lenth, int width) {
        return lenth * width;
    }

    public int areaSquare(int lenth) {
        return lenth * lenth;
    }

    public int areaTriangle(int cathetusOne, int cathetusTwo) {
        return (int) (cathetusOne * cathetusTwo / 2d);
    }
}
