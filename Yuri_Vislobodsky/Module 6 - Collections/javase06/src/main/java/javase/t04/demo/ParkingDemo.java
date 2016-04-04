package javase.t04.demo;

import javase.t04.parking.Car;
import javase.t04.parking.Parking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parking Demo Class
 * Created by Yury on 02.04.2016.
 */
public class ParkingDemo {
    private static Logger logger = LoggerFactory.getLogger(ParkingDemo.class);

    ParkingDemo() {}

    public static void main(String[] args) {
        Parking parking = new Parking(10);
        parking.arrival(new Car("23-45 AA"), 0);
        parking.arrival(new Car("46-21 AB"), 0);
        parking.arrival(new Car("15-03 AA"), 0);
        parking.arrival(new Car("66-21 AA"), 1);
        parking.arrival(new Car("38-44 AB"), 1);
        parking.departure(1);
        parking.departure(3);
        logger.info(parking.toString());
        logger.info("Free places count : " + parking.getFreePlacesCount());
        logger.info("Place ID=3 : " + parking.getCar(2));
        logger.info("Search car 15-03 AA : " + parking.searchCar(new Car("15-03 AA")));
    }
}
