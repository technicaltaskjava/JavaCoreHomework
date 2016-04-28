package com.epam.abstrfactory.ios;

import com.epam.abstrfactory.devise.Watch;

public class WatchIOS extends IOS implements Watch {
    private String type ="----------------WatchIOS------------------";

    public WatchIOS() {
        setType(type);
    }

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
