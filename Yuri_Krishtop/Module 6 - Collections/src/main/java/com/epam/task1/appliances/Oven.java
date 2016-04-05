package com.epam.task1.appliances;

/**
 * Created by Yuriy Krishtop on 02.04.2016.
 */
public class Oven extends Appliance {

    private int innerVol;

    public Oven(String manufacturer, int power, int price, int innerVol) {
        super(manufacturer, power, price);
        this.innerVol = innerVol;
    }

    @Override
    public String toString() {
        return super.toString() + "| inner volume: " + innerVol + " liter";
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
            Oven otherObj = (Oven) other;
            return (this.hashCode() == otherObj.hashCode()) && (this.innerVol == otherObj.innerVol);
        } catch (NullPointerException e) {
            log.error("Null pointer", e);
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode() + innerVol * 37;
    }
}
