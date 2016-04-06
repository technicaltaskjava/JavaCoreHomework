package com.epam.task1.appliances;

/**
 * Created by Yuriy Krishtop on 01.04.2016.
 */
public abstract class Appliance implements Comparable<Appliance> {
    protected String manufacturer;
    protected int power;
    protected int price;
    protected boolean switched = false;

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
    public int compareTo(Appliance other) {
        return this.getPower() - other.getPower();
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
        Appliance otherObj = (Appliance) other;
        return this.manufacturer.equals(otherObj.manufacturer) && (this.price == otherObj.price) &&
                (this.power == otherObj.power);
    }

    @Override
    public int hashCode() {
        return manufacturer.hashCode() + power + price;
    }

    @Override
    public String toString() {
        return getClass().getName().substring(26) + "| manufacturer: " + manufacturer + "| power: " + power +
                " watt per hour " + "| color: " + price + "$ ";
    }
}
