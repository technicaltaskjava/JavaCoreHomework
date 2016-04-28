package com.epam.abstrFactory.ios;

import com.epam.abstrFactory.devise.Phone;

public class PhoneIOS extends IOS implements Phone {
    public PhoneIOS() {
        setType(type);
    }
    private String type ="----------------PhoneIOS------------------";

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