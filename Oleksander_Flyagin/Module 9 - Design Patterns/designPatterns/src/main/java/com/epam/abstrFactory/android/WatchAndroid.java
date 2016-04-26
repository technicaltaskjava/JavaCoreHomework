package com.epam.abstrFactory.android;

import com.epam.abstrFactory.devise.Watch;

public class WatchAndroid extends AndroidOS implements Watch {
    public WatchAndroid() {
        setType(type);

    }

    private String type = "--------------WatchAndroid------------------";

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
