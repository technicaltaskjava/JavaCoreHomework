package com.epam.task1.appliances.cooler;

import com.epam.task1.appliances.Appliance;

/**
 * Created by Yuriy Krishtop on 02.04.2016.
 */
public abstract class Cooler extends Appliance {
    protected String coolant;
    protected int noiseLvl;

    public Cooler(String manufacturer, int power, int price, String coolant, int noiseLvl) {
        super(manufacturer, power, price);
        this.coolant = coolant;
        this.noiseLvl = noiseLvl;
    }

    @Override
    public String toString() {
        return super.toString() + "| coolant: " + coolant + "| noise level: " + noiseLvl + "dB ";
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
        Cooler otherObj = (Cooler) other;
        return (this.hashCode() == otherObj.hashCode()) && this.coolant.equals(otherObj.coolant);
    }

    @Override
    public int hashCode() {
        return 10000 + noiseLvl * 39;
    }

}


