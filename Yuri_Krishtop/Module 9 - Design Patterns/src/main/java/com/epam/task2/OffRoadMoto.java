package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class OffRoadMoto extends Motorcycle {

    public OffRoadMoto(Location location) {
        super(MotoType.OFFROAD, location);
        System.out.println("Making off-road motorcycle");
    }
}
