package com.epam.javase06.task04;

public class RunCarParking {
    private RunCarParking(){}

    public static void main(String[] args) {
        CarParking parking = new CarParking();
        System.out.println(parking.findFirstFreePlace());
        System.out.println(parking.countFreePlaces());
        System.out.println(parking.releasePlace(9));
    }
}
