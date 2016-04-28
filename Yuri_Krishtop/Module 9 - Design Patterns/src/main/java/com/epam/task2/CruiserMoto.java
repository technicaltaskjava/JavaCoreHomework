package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class CruiserMoto extends Motorcycle {

    public CruiserMoto(Location location) {
        super(MotoType.CRUISER, location);
        System.out.println("Making cruiser motorcycle");
    }
}
