package com.kokhanyuk.javase05.myjunit;

/**
 * AreaCalculator
 *
 *This class contains methods for calculating the area of some figures.
 *This class is the subject of testing.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class AreaCalculator {
    public double areaRange(int radius) throws NegativeRadiusException {
        double area = 0;
        if (radius> 0) {
         area = 3.14 * radius * radius;
         } else {
         throw new NegativeRadiusException();
         }
       // area=1/0;
        return area;
    }

    public int areaRectangle(int lenth, int width) {
        int area = lenth * width;

        return area;
    }

    public int areaSquare(int lenth) {
        int area = lenth * lenth;
        return area;
    }

    public double areaTriangle(int cathetusOne, int cathetusTwo) {
        double area = cathetusOne * cathetusTwo / 2;
        return area;

    }
}
