package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class SportBikeMoto extends Motorcycle {

    public SportBikeMoto(Location location) {
        super(MotoType.SPORTBIKE, location);
        System.out.println("Making sport bike motorcycle");
    }

}
