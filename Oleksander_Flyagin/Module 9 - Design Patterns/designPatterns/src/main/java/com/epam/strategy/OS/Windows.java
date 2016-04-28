package com.epam.strategy.OS;

public class Windows implements OS {
    private String os  = "Windows";
    private int amount = 850;

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
        System.out.println("Welcome to the Windows");
    }
}
