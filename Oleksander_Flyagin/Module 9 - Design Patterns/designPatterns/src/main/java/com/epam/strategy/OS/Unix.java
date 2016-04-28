package com.epam.strategy.os;

public class Unix implements OS {

    private String os  = "Unix";
    private int amount = 450;

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
        System.out.println("Welcome to the Linux system");
    }
}
