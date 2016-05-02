package com.epam.abstrfactory.ios;

import com.epam.abstrfactory.devise.Phone;

public class PhoneIOS extends IOS implements Phone {
    private String type ="----------------PhoneIOS------------------";
    public PhoneIOS() {
        setType(type);
    }


    @Override
    public void getCalle() {
        System.out.println(type);
        System.out.println("Hello! How are you?");

    }

    @Override
    public void getPhoto() {
        System.out.println(type);
        System.out.println("Your nuber's 099 99 999 999");
    }

}