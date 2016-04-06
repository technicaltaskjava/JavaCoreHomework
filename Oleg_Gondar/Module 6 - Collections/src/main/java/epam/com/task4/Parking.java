package epam.com.task4;

/**
 * Created by Oleg on 06.04.2016.
 */
public class Parking {

    public static final int N = 50;

    private Car[] places;

    public Parking() {
        places = new Car[N];
    }

    public boolean parkCar(int place, String carName) {

        for (int i = place; i < places.length; i++) {
            if (places[i] == null) {
                places[i] = new Car(carName);
                System.out.println("Car parked at " + i + " place");
                return true;
            }
        }
        System.out.println("Car not parked try again!");
        return false;
    }

    public int getFreePlaces() {
        int count = 0;
        for (Car car :
                places) {
            if (car == null) {
                count++;
            }
        }
        return count;
    }

    public int getCarPlace(String carName) {

        for (int i = 0; i < places.length; i++) {
            if (carName.equals(places[i].getName())) {
                return i;
            }
        }
        return -1;
    }

    public String freePlace(int number) {
        if (number < places.length) {
            places[number] = null;
            return "Place are free!";
        }
        return "Place not found";
    }

    public String freePlace(String carName) {
        for (int i = 0; i < places.length; i++) {
            if (carName.equals(places[i].getName())) {
                places[i] = null;
                return "Place are free!";
            }
        }
        return "Car not found";
    }

    public void showAll(){
        for (Car c:
             places) {
                if (c != null){
                    System.out.println(c.getName());
                }else System.out.println("Free");
        }
    }

}
