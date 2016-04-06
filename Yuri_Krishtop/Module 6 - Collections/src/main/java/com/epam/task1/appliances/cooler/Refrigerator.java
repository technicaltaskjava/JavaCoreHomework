package com.epam.task1.appliances.cooler;

/**
 * Created by Yuriy Krishtop on 02.04.2016.
 */
public class Refrigerator extends Cooler {
    private int cellCount;
    private int innerVolume;


    public Refrigerator(String manufacturer, int power, int price, String coolant,
                        int noiseLvl, int cellCount, int innerVolume) {
        super(manufacturer, power, price, coolant, noiseLvl);
        this.cellCount = cellCount;
        this.innerVolume = innerVolume;
    }

    @Override
    public String toString() {
        return super.toString() + "| count of cell: " + cellCount + "| inner volume: " + innerVolume + " liter ";
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
        Refrigerator otherObj = (Refrigerator) other;
        return (this.hashCode() == otherObj.hashCode()) && (this.innerVolume == otherObj.innerVolume);
    }

    @Override
    public int hashCode() {
        return 2000 + cellCount * 37 + innerVolume * 39;
    }

}
