package com.epam.abstrfactory.android;

import com.epam.abstrfactory.devise.Watch;

public class WatchAndroid extends AndroidOS implements Watch {
    private String type = "--------------WatchAndroid------------------";

    public WatchAndroid() {
        setType(type);

    }

    @Override
    public void getPuls() {
        System.out.println(type);
        System.out.println("Puls : 63");
    }

    @Override
    public void getCalories() {
        System.out.println(type);
        System.out.println("Calories : - 1500");

    }


}
