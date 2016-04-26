package com.epam.strategy.OS;

public class MacOS implements OS {
    private String os  = "OS X";
    private int amount = 950;

    @Override
    public String getOS() {
        return os;
    }

    @Override
    public int getAmountOfInformation() {
        return amount;
    }

    @Override
    public void getSalutation() {
        System.out.println("Welcome X");
    }

}
