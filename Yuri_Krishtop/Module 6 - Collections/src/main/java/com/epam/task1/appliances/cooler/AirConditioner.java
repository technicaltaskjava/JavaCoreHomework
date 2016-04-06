package com.epam.task1.appliances.cooler;

/**
 * Created by Yuriy Krishtop on 02.04.2016.
 */
public class AirConditioner extends Cooler {
    private double energyEfficiencyRatio;
    private int coolingPower;

    public AirConditioner(String manufacturer, int power, int price, String coolant,
                          int noiseLvl, double energyEfficiencyRatio, int coolingPower) {
        super(manufacturer, power, price, coolant, noiseLvl);
        this.coolingPower = coolingPower;
        this.energyEfficiencyRatio = energyEfficiencyRatio;
    }

    @Override
    public String toString() {
        return super.toString() + "| energy efficiency ratio: " + energyEfficiencyRatio +
                "| colling power: " + coolingPower + " watt ";
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
        AirConditioner otherObj = (AirConditioner) other;
        return (this.coolingPower == otherObj.coolingPower) && (this.hashCode() == otherObj.hashCode());
    }

    @Override
    public int hashCode() {
        return 1000 + coolingPower * 37;
    }

}

