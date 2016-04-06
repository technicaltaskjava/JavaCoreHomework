package com.epam.task1.appliances.cleaner;

/**
 * Created by Yuriy Krishtop on 02.04.2016.
 */
public class WashingMachine extends Cleaner {
    private double capacity;
    private int maximumSpinSpeed;

    public WashingMachine(String manufacturer, int power, int price, double waterConsumption,
                          int programCount, double capacity, int maximumSpinSpeed) {
        super(manufacturer, power, price, waterConsumption, programCount);
        this.capacity = capacity;
        this.maximumSpinSpeed = maximumSpinSpeed;
    }

    @Override
    public String toString() {
        return super.toString() + "| capacity: " + capacity + "kg " +
                "| maximum spin speed: " + maximumSpinSpeed + " rotations per min";
    }

    @Override
    public boolean equals(Object other) {
        if(other == null) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        WashingMachine otherObj = (WashingMachine) other;
        return (this.hashCode() == otherObj.hashCode()) && (this.maximumSpinSpeed == otherObj.maximumSpinSpeed);
    }

    @Override
    public int hashCode() {
        return 4000 + maximumSpinSpeed;
    }

}
