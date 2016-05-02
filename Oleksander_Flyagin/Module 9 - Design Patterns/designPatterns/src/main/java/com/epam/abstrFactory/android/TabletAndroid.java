package com.epam.abstrfactory.android;

import com.epam.abstrfactory.devise.Tablet;

public class TabletAndroid extends AndroidOS implements Tablet{
    private String type ="--------------TabletAndroid------------------";
    public TabletAndroid() {
        setType(type);
    }

    @Override
    public void getWorkDokuments() {
        System.out.println(type);
        System.out.println("Open googleDock");

    }

}
