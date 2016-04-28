package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public class StandardMoto extends Motorcycle {

    public StandardMoto(Location location) {
        super(MotoType.STANDARD, location);
        System.out.println("Making standard motorcycle");
    }
}
