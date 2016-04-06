package com.epam.task1.appliances.cleaner;

/**
 * Created by Yuriy Krishtop on 02.04.2016.
 */
public class DishwasherMachine extends Cleaner {

    private int capacitySets;

    public DishwasherMachine(String manufacturer, int power, int price,
                             double waterConsumption, int programCount, int capacityKit) {
        super(manufacturer, power, price, waterConsumption, programCount);
        this.capacitySets = capacityKit;
    }

    @Override
    public String toString() {
        return super.toString() + "| capacity of sets : " + capacitySets;
    }

    @Override
    public boolean equals(Object other) {
        try {
            if (!super.equals(other)) {
                return false;
            }
            if (this.getClass() != other.getClass()) {
                return false;
            }
            DishwasherMachine otherObj = (DishwasherMachine) other;
            return (this.hashCode() == otherObj.hashCode()) && (this.capacitySets == otherObj.capacitySets);
        } catch (NullPointerException e) {
            log.error("Null pointer", e);
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 3000 + capacitySets * 41;
    }
}
