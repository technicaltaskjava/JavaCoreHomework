package com.task1.transport.planes;

/**
 * @author Sergey Solovyov
 */

public abstract class AbstractPlane{

    private int passengerCapacity;
    private int flightRange;
    private double luggageWeight;


    private String colour;


    public AbstractPlane(int flightRange, double luggageWeight, int passengerCapacity, String colour) {
        this.flightRange = flightRange;
        this.luggageWeight = luggageWeight;
        this.passengerCapacity = passengerCapacity;
        this.colour = colour;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("flightRange=");
        stringBuilder.append(flightRange);
        stringBuilder.append(", passengerCapacity=");
        stringBuilder.append(passengerCapacity);
        stringBuilder.append(", luggageWeight=");
        stringBuilder.append(String.format("%.3f",luggageWeight)+" tons");
        stringBuilder.append(", colour=");
        stringBuilder.append(colour);
        stringBuilder.append(",");

        return   stringBuilder.toString();
    }

    public int getFlightRange() {
        return flightRange;
    }

    public String getColour() {
        return colour;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public double getLuggageWeight() {
        return luggageWeight;
    }
}

