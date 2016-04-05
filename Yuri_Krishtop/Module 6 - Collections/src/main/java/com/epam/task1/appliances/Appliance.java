package com.epam.task1.appliances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Yuriy Krishtop on 01.04.2016.
 */
public abstract class Appliance implements Comparable<Appliance> {
    protected String manufacturer;
    protected int power;
    protected int price;
    protected boolean switched = false;
    protected static final Logger log = LoggerFactory.getLogger(Appliance.class);

    public Appliance(String manufacturer, int power, int price) {
        this.manufacturer = manufacturer;
        this.power = power;
        this.price = price;
    }

    public void switchOn() {
        switched = true;
    }

    public void switchOff() {
        switched = false;
    }

    public int getPower() {
        return power;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSwitched() {
        return switched;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public int compareTo(Appliance other) {
        try {
            return this.getPower() - other.getPower();
        } catch (NullPointerException e) {
            log.error("Null pointer", e);
            return 0;
        }

    }

    @Override
    public boolean equals(Object other) {
        try {
            if(!super.equals(other)){
                return false;
            }
            if(this.getClass() != other.getClass()) {
                return false;
            }
            Appliance otherObj = (Appliance)other;
            return this.manufacturer.equals(otherObj.manufacturer) && (this.price == otherObj.price) &&
                    (this.power == otherObj.power);
        } catch (NullPointerException e) {
            log.error("Null pointer", e);
            return false;
        }

    }

    @Override
    public int hashCode()    {
        return manufacturer.hashCode()+power+price;
    }

    @Override
    public String toString() {
        return getClass().getName().substring(26) + "| manufacturer: " + manufacturer + "| power: " + power +
                " watt per hour " + "| color: " + price + "$ ";
    }
}
