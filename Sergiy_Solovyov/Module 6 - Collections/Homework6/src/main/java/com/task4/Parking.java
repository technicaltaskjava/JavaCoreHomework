package com.task4;

/**
 * @author Sergey Solovyov
 */
public class Parking {

    private Car [] cars;

    public Parking(int capacity){
        cars = new Car[capacity];
        System.out.println("Parking created, " + capacity + " places are available");
    }
    public boolean parkCar(Car car){
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                cars[i] = car;
                System.out.println("New car parked: " + car.getCarNumber() +  " -  car number, " + car.getOwnerName()+ " - owner");
                return true;
            }
        }
        System.out.println("Parking is full");
      return false;
    }

    public boolean leaveParking(Car car) throws NoSuchCarException {

        for (int i = 0; i < cars.length; i++) {
            if (car.equals(cars[i])) {
                cars[i] = null;
                System.out.println("Car left parking: " + car.getCarNumber() +  " - car number, " + car.getOwnerName()+ " - owner");
                return true;}
        }
        throw new NoSuchCarException("Car " + car + " not found");
    }
    public int getFreePlaces(){
        int quantity = 0;

        for (Car car : cars) {
            if (car == null)
                quantity++;
        }
        return quantity;
    }
    public int getPlaceNumber(Car car) throws NoSuchCarException {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null && cars[i].equals(car)){
                int place = i + 1;
                System.out.println("Parking place of car( "+ car.getCarNumber() +
                        " - car number, " + car.getOwnerName()+ " - owner) is - " + place);
                return place;}
        }
        throw new NoSuchCarException(car + " not found");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parking contains next cars: \n");
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null){
                sb.append(cars[i]);
            }
        }
        return sb.toString();
    }
}
