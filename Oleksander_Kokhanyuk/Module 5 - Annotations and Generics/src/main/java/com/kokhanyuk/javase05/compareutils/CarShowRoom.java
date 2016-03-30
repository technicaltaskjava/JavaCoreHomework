package com.kokhanyuk.javase05.compareutils;


import com.kokhanyuk.javase05.CompareUtils;

/**
 * CarShowRoom
 * <p/>
 * This class uses CompareUtils from sort cars
 * It is necessary to test the operation of other parts of the homework.
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
public class CarShowRoom {
    private CarShowRoom() {
    }
    public static void main(String[] args) {

        Car[] showCase = new Car[6];
        showCase[0] = new Car("Mers", 11500);
        showCase[1] = new Car("Audi", 14000);
        showCase[2] = new Car("BMW", 18000);
        showCase[3] = new Car("Opel", 12200);
        showCase[4] = new Car("Ford", 16300);
        showCase[5] = new Car("Opel", 11000);
        for (Car car : showCase) {
            System.out.println(car);
        }
        CompareUtils<Car> sort = new CompareUtils();
        System.out.println("\n" + sort.max(showCase, new SortedByName()));
    }
}
