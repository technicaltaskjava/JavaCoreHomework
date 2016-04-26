package com.epam.abstrFactory.ios;

import com.epam.abstrFactory.devise.Tablet;

public class TabletIOS  extends IOS implements Tablet {
    public TabletIOS() {
        setType(type);
    }
    private String type ="----------------TabletIOS------------------";

    @Override
    public void getWorkDokuments() {
        System.out.println(type);
        System.out.println("Open documents");

    }


}
