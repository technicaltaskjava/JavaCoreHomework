package com.task4;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergey Solovyov
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private Main(){}

    public static void main(String[] args)  {
        Parking parking = new Parking(10);
        Car car = new Car("AA3696FE", "Bob");
        Car car2 = new Car("PP9966", "John");
        Car car3 = new Car("IO8959LL", "Alex");
        parking.parkCar(car);
        parking.parkCar(car2);
        parking.parkCar(car3);
        System.out.println(parking.toString());
        Car car4 = new Car("PP5559LL", "Alicia");
        try {
            parking.getPlaceNumber(car);
            parking.getPlaceNumber(car2);
            parking.getPlaceNumber(car3);
            parking.leaveParking(car);
            System.out.println(parking.toString());
            System.out.println("Attempt to find car that doesn't exist\n\n");
            System.out.println(parking.getPlaceNumber(car4));
        } catch (NoSuchCarException e) {
            LOGGER.info(e.getClass().toString(), e);
        }


    }
}
