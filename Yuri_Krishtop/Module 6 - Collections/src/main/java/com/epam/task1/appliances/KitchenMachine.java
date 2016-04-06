package com.epam.task1.appliances;

/**
 * Created by Yuriy Krishtop on 02.04.2016.
 */
public class KitchenMachine extends Appliance {
    private double bowlSize;
    private String caseStuff;

    public KitchenMachine(String manufacturer, int power, int price, double bowlSize,
                          String caseStuff) {
        super(manufacturer, power, price);
        this.bowlSize = bowlSize;
        this.caseStuff = caseStuff;
    }

    @Override
    public String toString() {
        return super.toString() + "| size of bowl: " + bowlSize + " liter " + "| stuff of case: " + caseStuff;
    }

    @Override
    public boolean equals(Object other) {
        if(other == null) {
            return false;
        }
        if(!super.equals(other)){
            return false;
        }
        if(this.getClass() != other.getClass()) {
            return false;
        }
        KitchenMachine otherObj = (KitchenMachine)other;
        return (this.hashCode() == otherObj.hashCode()) && this.caseStuff.equals(otherObj.caseStuff);
    }

    @Override
    public int hashCode()    {
        return super.hashCode()+caseStuff.hashCode();
    }

}
