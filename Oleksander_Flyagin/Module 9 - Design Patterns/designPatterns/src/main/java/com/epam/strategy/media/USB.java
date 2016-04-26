package com.epam.strategy.media;

import com.epam.strategy.OS.OS;
import com.epam.strategy.strategyOS.SettingOS;

public class USB implements SettingOS {
    private int speed = 120;


    @Override
    public void setOS(OS os) {
        System.out.println("Found storage device with OS " + os.getOS());
        System.out.println("Run verifications storage device");
        int amount = os.getAmountOfInformation();

        do {
            amount-=speed;

            if(amount<0)
            {
                System.out.println("Loading data");
            }
            else
                System.out.println("Left to complete " + amount);
        }
        while (amount>0);

        System.out.println("Preparing to install");
        boolean setupSuccessful = false;

        while (!setupSuccessful)
        {
            System.out.println("Installation completed");
            setupSuccessful = true;
        }
        os.getSalutation();

    }
}
