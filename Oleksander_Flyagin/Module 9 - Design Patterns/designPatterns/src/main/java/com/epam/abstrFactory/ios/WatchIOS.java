package com.epam.abstrFactory.ios;

import com.epam.abstrFactory.devise.Watch;

public class WatchIOS extends IOS implements Watch {
    public WatchIOS() {
        setType(type);
    }

    private String type ="----------------WatchIOS------------------";

    @Override
    public void getPuls() {
        System.out.println(type);
        System.out.println("Puls : 67");

    }


   @Override
    public void getCalories() {
        System.out.println(type);
        System.out.println("Calories : - 1500");

    }




}
