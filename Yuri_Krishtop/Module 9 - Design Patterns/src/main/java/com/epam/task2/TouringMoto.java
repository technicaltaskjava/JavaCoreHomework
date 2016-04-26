package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class TouringMoto extends Motorcycle {

    public TouringMoto(Location location) {
        super(MotoType.TOURING, location);
        System.out.println("Making touring motorcycle");
    }
}
