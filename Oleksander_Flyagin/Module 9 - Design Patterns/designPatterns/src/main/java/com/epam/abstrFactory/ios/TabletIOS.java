package com.epam.abstrfactory.ios;

import com.epam.abstrfactory.devise.Tablet;

public class TabletIOS  extends IOS implements Tablet {
    private String type ="----------------TabletIOS------------------";
    public TabletIOS() {
        setType(type);
    }

    @Override
    public void getWorkDokuments() {
        System.out.println(type);
        System.out.println("Open documents");

    }


}
