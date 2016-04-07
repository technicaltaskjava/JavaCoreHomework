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

    public String getCarName(int pos) {
        if (pos >= places.length) {
            return "";
        }
        return places[pos].getName();
    }

    public String parkCar(int place, String carName) {
        if (place >= places.length) {
            return "Your place out of bound!";
        }

        String chek = chekCarNameNotExist(carName);

        if ("not".equals(chek)) {

            for (int i = place; i < places.length; i++) {
                if (places[i] == null) {
                    places[i] = new Car(carName);
                    return "Car parked at " + i + " place";
                }
            }
        } else if ("Car with given name already parked, name of car can not be same".equals(chek)) {
            return chek;
        }
        return "Car not parked try again!";
    }

    public boolean isPlaceFree(int i) {
        return (places[i] == null) ? true : false;
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
            if (places[i] != null && carName.equals(places[i].getName())) {
                return i;
            }
        }
        return -1;
    }

    public String unparkCar(int number) {
        if (number < places.length) {
            places[number] = null;
            return "Place are free!";
        }
        return "Place not found";
    }

    public String unparkCar(String carName) {
        for (int i = 0; i < places.length; i++) {
            if (carName.equals(places[i].getName())) {
                places[i] = null;
                return "Place are free!";
            }
        }
        return "Car not found";
    }

    private String chekCarNameNotExist(String carName) {

        for (int i = 0; i < places.length; i++) {
            if (places[i] != null && carName.equals(places[i].getName())) {
                return "Car with given name already parked, name of car can not be same";
            }
        }
        return "not";
    }

}
