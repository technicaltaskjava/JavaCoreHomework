package com.epam.task1.appliances.cleaner;

import com.epam.task1.appliances.Appliance;

/**
 * Created by Yuriy Krishtop on 02.04.2016.
 */
public abstract class Cleaner extends Appliance {
    protected double waterConsumption;
    protected int programCount;

    public Cleaner(String manufacturer, int power, int price, double waterConsumption,
                   int programCount) {
        super(manufacturer, power, price);
        this.waterConsumption = waterConsumption;
        this.programCount = programCount;
    }

    @Override
    public String toString() {
        return super.toString() + "| water consumption: " + waterConsumption + "liter per cycle" +
                "| count of programs: " + programCount;
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
        Cleaner otherObj = (Cleaner) other;
        return (this.hashCode() == otherObj.hashCode()) && (this.programCount == otherObj.programCount);
    }

    @Override
    public int hashCode() {
        return 2000 + programCount * 37;
    }
}
