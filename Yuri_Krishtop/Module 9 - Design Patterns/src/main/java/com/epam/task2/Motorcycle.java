package com.epam.task2;

/**
 * Created by Yuriy Krishtop on 24.04.2016.
 */
public abstract class Motorcycle {

    private MotoType type;
    private Location location;

    public Motorcycle(MotoType type, Location location) {
        this.type = type;
        this.location = location;
    }

    public MotoType getType() {
        return type;
    }

    public void setType(MotoType type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Type of motorcycle - " + type + " made in " + location;
    }
}
