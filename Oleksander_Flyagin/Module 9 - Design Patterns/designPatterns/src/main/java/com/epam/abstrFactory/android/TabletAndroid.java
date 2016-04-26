package com.epam.abstrFactory.android;

import com.epam.abstrFactory.devise.Tablet;

public class TabletAndroid extends AndroidOS implements Tablet{
    public TabletAndroid() {
        setType(type);
    }
    private String type ="--------------TabletAndroid------------------";

    @Override
    public void getWorkDokuments() {
        System.out.println(type);
        System.out.println("Open googleDock");

    }

}
