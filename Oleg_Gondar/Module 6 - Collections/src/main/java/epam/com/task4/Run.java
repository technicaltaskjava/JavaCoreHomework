package epam.com.task4;

/**
 * Created by Oleg on 06.04.2016.
 */
public class Run {
    private Run() {
    }

    public static void main(String[] args) {
        Parking parking = new Parking();
        parking.parkCar(3,"test");

        parking.parkCar(3,"test1");

        parking.showAll();
        System.out.println(parking.getFreePlaces());

    }

}
